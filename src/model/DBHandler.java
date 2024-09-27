package model;

import java.sql.*;
import java.util.*;



public class DBHandler {
	
	protected static Connection con;
	private static String userName = "admin";
	private static String password = "admin";
	private static int port = 3306;
	private static String dbName = "dbForAccountingSystem";
	private static String host = "localhost";
	
	
	
	
	//connection section to DB
	 
	public static boolean openConnection() 
	{
		try {
			// Connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url =  "jdbc:mysql://"+host+":"+port+"/"+dbName;
			con = DriverManager.getConnection(url,userName,password);
			return true;
		}catch(Exception e) 
		{
			
			return false;
		}
	}
	
	public static boolean closeConnection() 
	{
		try {
			con.close();
			return true;
		}catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//-------------------------------------------
	
	
	
	
	// User Section
	
	public static boolean addUser(String username,String password,int role)
	{
		try {
			openConnection();
			password = Checker.digistMsg(password);
			String sql = "INSERT INTO users(username,password,role) VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, role);
			ps.executeUpdate();
			closeConnection();
			return true;
		}catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//-------------------------------------------
	
	
	
	
	// Category Section
	
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
	
	public static  Category getCategory(String name)
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
	
	public static  Category getCategory(int id)
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
	
	//-------------------------------------------
	
	
	
	
	// Item Section
	
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
				Item i = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),getCategory(rs.getInt(4)).getName(),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8),rs.getInt(9),rs.getDate(10),rs.getDate(11));
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
			ps.setInt(3,getCategory(category).getId());
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
				i = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),getCategory(rs.getInt(4)).getName(),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8),rs.getInt(9),rs.getDate(10),rs.getDate(11));
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
				i = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),getCategory(rs.getInt(4)).getName(),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8),rs.getInt(9),rs.getDate(10),rs.getDate(11));
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
				i = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),getCategory(rs.getInt(4)).getName(),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8),rs.getInt(9),rs.getDate(10),rs.getDate(11));
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
			ps.setInt(3,getCategory(category).getId());
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
	
	//-------------------------------------------
	
	
	
	
	// Warehouse Section
	
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
	
	
	//-------------------------------------------
	
	
	
	
	
	
	// Supplier Section
	

	public static ArrayList<Supplier> getAllSupplier() 
	{
		ArrayList<Supplier> al = new ArrayList<>();
		try {
			openConnection();
			String sql = "SELECT contacts.id,contacts.email,contacts.phone,contacts.address,people.id,people.name,people.status,people.created_at FROM people "
					+ "LEFT OUTER JOIN contacts "
					+ "ON people.contact_id = contacts.id "
					+ "WHERE people.type = 2;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{		
				Contact c = new Contact(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				Supplier s = new Supplier(rs.getInt(5),rs.getString(6),c,rs.getInt(7),"admin",rs.getDate(8));
				al.add(s);	
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
			String sql = "SELECT contacts.id,contacts.email,contacts.phone,contacts.address,people.id,people.name,people.status,people.created_at FROM people "
					+ "LEFT OUTER JOIN contacts "
					+ " ON people.contact_id = contacts.id "
					+ "WHERE people.type = 2 And people.name = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Contact c = new Contact(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				s = new Supplier(rs.getInt(5),rs.getString(6),c,rs.getInt(7),"admin",rs.getDate(8));
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
	
	//-------------------------------------------
	
	
	
	
	
	
		// Purchase Section
	
	
	
	
	public static void main(String[] args) {
		
	}
	
		
	

}
