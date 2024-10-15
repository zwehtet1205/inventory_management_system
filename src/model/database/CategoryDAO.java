package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Category;

public class CategoryDAO extends DBHandler{
	
	public static ArrayList<Category> getAllCategory() 
	{
		ArrayList<Category> al = new ArrayList<>();
		try {
			openConnection();
			String sql = "SELECT * FROM categories";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Category c = new Category(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
				al.add(c);	
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
	public static ArrayList<String> getAllCategoryName() 
	{
		ArrayList<String> al = new ArrayList<>();
		try {
			openConnection();
			String sql = "SELECT name FROM categories WHERE status = 1";
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
	
	public static Category getCategory(String name)
	{
		Category c = null;
		try {
			openConnection();
			String sql = "SELECT * FROM categories where name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				c = new Category(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return c;
	}
	
	public static Category getCategory(int id)
	{
		Category c = null;
		try {
			openConnection();
			String sql = "SELECT * FROM categories where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				c = new Category(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return c;
	}
	
	
	
	public static void addCategoryDAO(String name,String description,int status)
	{
		try {
			openConnection();
			String sql = "INSERT INTO categories(name,description,status) VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setInt(3,status);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static boolean existCategorys(String name)
	{
		try {
			openConnection();
			String sql = "SELECT COUNT(*) FROM categories WHERE name = ?";
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
	
	public static void updateCategoryDAO(int target_id,String name,String description,int status)
	{
		try {
			openConnection();
			String sql = "UPDATE categories SET name = ?, description = ?, status = ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, description);
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
