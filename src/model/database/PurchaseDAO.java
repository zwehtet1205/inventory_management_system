package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Purchase;


public class PurchaseDAO extends DBHandler{

	
	
	
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
