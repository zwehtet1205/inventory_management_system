package libraries;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

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
	
	public static boolean isValidName(String n)
	{
		String reg= "^[A-Z][a-z]*(\s[A-Z][a-z]+)*";
		
		return Pattern.matches(reg, n);
	}
	
	public static String digistMsg(String str)
	{
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			return new String(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return "unsuccess";
		}
		
	}

}
