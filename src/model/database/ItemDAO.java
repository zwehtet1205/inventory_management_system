package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Item;

public class ItemDAO extends DBHandler{
	
	public static ArrayList<Item> getAllItems() 
	{
		ArrayList<Item> al = new ArrayList<>();
		try {
			openConnection();
			String sql = "SELECT * FROM items";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Item i = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getDate(8),rs.getDate(9));
				al.add(i);	
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
	public static boolean existItems(String name)
	{
		try {
			openConnection();
			String sql = "SELECT COUNT(*) FROM items WHERE name = ?";
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
	
	public static boolean existItem(String code)
	{
		try {
			openConnection();
			String sql = "SELECT COUNT(*) FROM items WHERE code = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
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
	
	public static void addItemsDAO(String code,String name,String category,int status)
	{
		try {
			
			openConnection();
			String sql = "INSERT INTO items (code,name,category_id,status,created_by) VALUES(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setInt(3,CategoryDAO.getCategory(category).getId());
			ps.setInt(4, status);
			ps.setInt(5, 1);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static Item getItem(String code)
	{
		Item i = null;
		try {
			openConnection();
			String sql = "SELECT * FROM items WHERE code = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				i = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getDate(8),rs.getDate(9));
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return i;
	}
	
	public static Item getItem(int id)
	{
		Item i = null;
		try {
			openConnection();
			String sql = "SELECT * FROM items WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);;
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				i = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getDate(8),rs.getDate(9));
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return i;
	}
	
	public static Item getItems(String name)
	{
		Item i = null;
		try {
			openConnection();
			String sql = "SELECT * FROM items WHERE name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				i = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getDate(8),rs.getDate(9));
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return i;
	}
	
	public static void updateItemDAO(int target_id,String code,String name,String category,int status)
	{
		try {
			openConnection();
			String sql = "UPDATE items SET code = ?, name = ?, category_id = ?, status = ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setInt(3,CategoryDAO.getCategory(category).getId());
			ps.setInt(4, status);
			ps.setInt(5,target_id);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static void updatePrice(String code,double price)
	{
		try {
			openConnection();
			String sql = "UPDATE items SET price = ? WHERE code = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, price);
			ps.setString(2,code);
			ps.executeUpdate();
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static int getRemainingQty(int warehouse_id,int item_id)
	{
		int remainingQty = 0;
		try {
			openConnection();
			String sql = "SELECT getremainingqty_fun(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, warehouse_id);
			ps.setInt(2, item_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				remainingQty = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return remainingQty;
	}

}
