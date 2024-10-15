package model.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Purchase;


public class PurchaseDAO extends DBHandler{

	public static ArrayList<String> getAllSupplierName() 
	{
		ArrayList<String> al = new ArrayList<>();
		try {
			openConnection();
			String sql = "SELECT name FROM people WHERE status = 1 and type = 2";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				al.add(rs.getString(1));	
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
	public static ArrayList<String> getAllWarehouseName() 
	{
		ArrayList<String> al = new ArrayList<>();
		try {
			openConnection();
			String sql = "SELECT name FROM warehouses WHERE status = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				al.add(rs.getString(1));	
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
	public static ArrayList<String> getAllPaymentType() 
	{
		ArrayList<String> al = new ArrayList<>();
		try {
			openConnection();
			String sql = "SELECT type FROM payments WHERE status = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				al.add(rs.getString(1));	
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
	public static ArrayList<Purchase> getAllPurchases() 
	{
		ArrayList<Purchase> al = new ArrayList<>();
		try {
			openConnection();
			String sql ="SELECT id,invoice_id,people_id,warehouse_id,item_id,qty,price FROM processes WHERE type = 2";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{		
				Purchase p = new Purchase(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDouble(7),1);
				al.add(p);
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	// addpurchase_fun( p_invoiceNumber VARCHAR(100), p_invoiceDate DATE, p_payment VARCHAR(50), p_discount DECIMAL(10,2), p_people VARCHAR(50), p_warehouse VARCHAR(50), p_code VARCHAR(50), p_name VARCHAR(50), p_qty INT, p_price DECIMAL(10,2), p_created_by INT)
	public static void addPurchase(int invoice_id,String people,String warehouse,String code,String name,int qty,double price,int created_by)
	{
		try {
			
			openConnection();
			String sql = "CALL addpurchase_proc(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, invoice_id);
			ps.setString(2, people);
			ps.setString(3, warehouse);
			ps.setString(4, code);
			ps.setString(5, name);
			ps.setInt(6, qty);
			ps.setDouble(7, price);
			ps.setInt(8, created_by);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static int addVoucher(String invoiceNumber,Date invoiceDate,String payment,double discount,int created_by)
	{
		int id = -1;
		try {
			
			openConnection();
			String sql = "SELECT addvoucher_fun(?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, invoiceNumber);
			ps.setDate(2, invoiceDate);
			ps.setString(3, payment);
			ps.setDouble(4, discount);
			ps.setInt(5, created_by);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{		
				id = rs.getInt(1);	
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
			return -1;
		}
		return id;
	}
	
	public static Purchase getPurchase(int id)
	{
		Purchase p = null;
		try {
			openConnection();
			String sql = "SELECT id,invoice_id,people_id,warehouse_id,item_id,qty,price FROM processes WHERE type = 2 AND id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				p = new Purchase(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDouble(7),1);
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return p;
	}
	
	public static int getNoOfInvoices()
	{
		int c = 1;
		try {
			openConnection();
			String sql = "SELECT COUNT(*) FROM invoices";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				c += rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return c;
	}
	
	public static double getSubTotal()
	{
		double d = 0;
		try {
			openConnection();
			String sql = "SELECT SUM(total) FROM purchases";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				d += rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return d;
	}
	
	
	
	
}
