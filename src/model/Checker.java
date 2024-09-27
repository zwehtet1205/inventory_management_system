package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Checker {
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
