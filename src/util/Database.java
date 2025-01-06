package util;

import java.sql.*;

public class Database {
	
	private static final String userName = Config.USERNAME;
    private static final String password = Config.PASSWORD;
    private static final String port = Config.PORT;
    private static final String dbName = Config.DBNAME;
    private static final String host = Config.HOST;
    
    public static Connection connect() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        return DriverManager.getConnection(url, userName, password);
    }
    
}
