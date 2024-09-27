package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			String sql ="SELECT id,item_id,qty,price,total,status FROM processes WHERE type = 2";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{		
				Purchase p = new Purchase(rs.getInt(1),DBHandler.getItem(rs.getInt(2)),rs.getInt(3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),1);
				al.add(p);
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
	public static int addPurchase(String code,String name,int qty,double price,int status)
	{
		int id = -1;
		try {
			
			openConnection();
			String sql = "SELECT addpurchase_fun(?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setInt(3,qty);
			ps.setDouble(4, price);
			ps.setInt(5, status);
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
			String sql = "SELECT id,item_id,qty,price,total,status FROM processes WHERE type = 2 AND id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				p = new Purchase(rs.getInt(1),DBHandler.getItem(rs.getInt(2)),rs.getInt(3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),1);
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
