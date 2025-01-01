package model.dao;

import java.sql.PreparedStatement;

import model.entities.Checker;

public class UserDAO extends DBHandler{
	
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

}
