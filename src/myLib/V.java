package myLib;

import javafx.scene.control.*;
import javafx.scene.image.*;

public class V {
	/**
	 * v is extends for View 
	 * @param label = to create label
	 * v = to create view, $ = to create nodes, __ = to update nodes
	 */
	
	public static void $(Label label)
	{
		label = new Label();
	}
	
	public static void $(Label label,String str)
	{
		label = new Label(str);
	}
	
	public static void $(Label label,ImageView icon,String str)
	{
		$(label);
		__(label,icon);
		__(label," "+str);
	}
	
	
	
	
	public static void __(Label label,String str)
	{
		label.setText(str);
	}
	
	public static void __(Label label,ImageView icon)
	{
		label.setGraphic(icon);
	}
	

}
