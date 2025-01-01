package model.entities;

public class Validation {
	
	public static boolean isNum(String s)
	{
		
		try {
			Double.parseDouble(s);
			return true;
		}
		catch(NumberFormatException ex)
		{
			
			return false;
		}
		
	}

}
