package util;

import java.sql.*;

public class Database {
	
	private static final String userName = "admin";
    private static final String password = "admin";
    private static final int port = 3306;
    private static final String dbName = "dbForAccountingSystem";
    private static final String host = "localhost";
    
    public static Connection connect() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        return DriverManager.getConnection(url, userName, password);
    }
    
}
