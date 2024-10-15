package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.*;

public class WarehouseDAO extends DBHandler{
	
	public static ArrayList<Warehouse> getAllWarehouse() 
	{
		ArrayList<Warehouse> al = new ArrayList<>();
		try {
			openConnection();
			String sql = "SELECT * FROM warehouses";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Warehouse w = new Warehouse(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
				al.add(w);	
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
	public static  Warehouse getWarehouse(String name)
	{
		Warehouse w = null;
		try {
			openConnection();
			String sql = "SELECT * FROM warehouses where name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				w = new Warehouse(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return w;
	}
	
	public static  Warehouse getWarehouse(int id)
	{
		Warehouse w = null;
		try {
			openConnection();
			String sql = "SELECT * FROM categories where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				w = new Warehouse(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return w;
	}
	
	
	
	public static void addWarehouseDAO(String name,String location,int status)
	{
		try {
			openConnection();
			String sql = "INSERT INTO warehouses(name,location,status) VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, location);
			ps.setInt(3,status);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static boolean existWarehouse(String name)
	{
		try {
			openConnection();
			String sql = "SELECT COUNT(*) FROM warehouses WHERE name = ?";
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
	
	public static void updateWarehouseDAO(int target_id,String name,String location,int status)
	{
		try {
			openConnection();
			String sql = "UPDATE categories SET name = ?, description = ?, status = ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, location);
			ps.setInt(3,status);
			ps.setInt(4, target_id);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

}
