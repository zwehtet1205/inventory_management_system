package model.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InvoiceDAO extends DBHandler{
	
	public static int getNoOfPurchases()
	{
		int c = 1;
		try {
			openConnection();
			String sql = "SELECT COUNT(*) FROM invoices where type = 2";
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
	
	public static int getNoOfSales()
	{
		int c = 1;
		try {
			openConnection();
			String sql = "SELECT COUNT(*) FROM invoices where type = 1";
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
	
	public static int addVoucher(String invoiceNumber,Date invoiceDate,int type,String payment,double discount,int created_by)
	{
		int id = -1;
		try {
			
			openConnection();
			String sql = "SELECT addvoucher_fun(?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, invoiceNumber);
			ps.setDate(2, invoiceDate);
			ps.setInt(3, type);
			ps.setString(4, payment);
			ps.setDouble(5,discount);
			ps.setInt(6, created_by);
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

}
