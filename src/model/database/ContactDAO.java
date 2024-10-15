package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.*;

public class ContactDAO extends DBHandler {
	public static Contact getContact(int id)
	{
		Contact c = null;
		try {
			openConnection();
			String sql = "SELECT * FROM contacts where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				c = new Contact(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return c;
	}
}
