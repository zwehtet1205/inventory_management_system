package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import model.*;

public class SupplierDAO extends DBHandler {
	
	public static ArrayList<Supplier> getAllSupplier() 
	{
		ArrayList<Supplier> al = new ArrayList<>();
		try {
			openConnection();
			String sql = "SELECT id,name,contact_id,status,created_by,created_at  FROM people WHERE type = 2";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{	
				Supplier s = new Supplier(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDate(6));
				al.add(s);	
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
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
	
	public static boolean existSupplier(String name)
	{
		try {
			openConnection();
			String sql = "SELECT COUNT(*) FROM people WHERE name = ? and type = 2";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int i = rs.getInt(1);
				if(i < 1) {
					return false;
				}
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return true;
	}

	public static void addSupplierDAO(String name,String email,String phone, String address,int status)
	{
		try {
			openConnection();
			String sql = "CALL addsupplier_proc(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setInt(5,status);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static  Supplier getSupplier(String name)
	{
		Supplier s = null;
		try {
			openConnection();
			String sql = "SELECT id,name,contact_id,status,created_by,created_at FROM people WHERE name = ? and type = 2 ";
					
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				s = new Supplier(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDate(6));
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return s;
	}
	
	public static void updateSupplierDAO(int target_id,String name,String email,String phone,String address,int status)
	{
		try {
			openConnection();
			String sql ="CALL updatesupplier_proc(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, target_id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setString(5, address);	
			ps.setInt(6,status);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

}
