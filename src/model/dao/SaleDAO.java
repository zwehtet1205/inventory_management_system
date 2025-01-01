package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.entities.*;

public class SaleDAO extends DBHandler{
	
	public static ArrayList<Sale> getAll() 
	{
		ArrayList<Sale> al = new ArrayList<>();
		try {
			openConnection();
			String sql ="SELECT id,invoice_id,people_id,warehouse_id,item_id,qty FROM processes WHERE type = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{		
				Sale s = new Sale(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),1);
				al.add(s);
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
//	addsale_proc( IN p_invoice_id INT, IN p_people VARCHAR(50), IN p_warehouse VARCHAR(50), IN p_code VARCHAR(50), IN p_name VARCHAR(50), IN p_qty INT, IN p_created_by INT )
	public static void add(int invoice_id,String people,String warehouse,String code,String name,int qty,int created_by)
	{
		try {
			
			openConnection();
			String sql = "CALL addsale_proc(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, invoice_id);
			ps.setString(2, people);
			ps.setString(3, warehouse);
			ps.setString(4, code);
			ps.setString(5, name);
			ps.setInt(6, qty);
			ps.setDouble(7, created_by);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
//	public static Purchase getSales(int id)
//	{
//		Purchase p = null;
//		try {
//			openConnection();
//			String sql = "SELECT id,invoice_id,people_id,warehouse_id,item_id,qty,price FROM processes WHERE type = 2 AND id = ?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next())
//			{
//				p = new Purchase(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDouble(7),1);
//			}
//			closeConnection();
//		}catch(Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return p;
//	}

}
