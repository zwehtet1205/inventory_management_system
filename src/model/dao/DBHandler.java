package model.dao;

import java.sql.*;


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
	
	
	
	
	// Warehouse Section
	
	
	
	

	public static void main(String[] args) {
		
	}
	
		
	

}
