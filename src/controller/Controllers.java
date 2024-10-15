package controller;

import model.database.*;

public class Controllers {

	public static void openDBConnection()
	{
		try {
			DBHandler.openConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeDBConnection()
	{
		try {
			DBHandler.closeConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
