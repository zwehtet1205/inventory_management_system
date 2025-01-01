package libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.*;

public class Icon {
	private static String dir = "resources/img/",imgExtension = ".png";
	private static FileInputStream f;
	private static ImageView imv;
	
	public static ImageView get(String filename,int size)
	{
		try {
			f = new FileInputStream(dir+filename+imgExtension);
			imv = new ImageView(new Image(f));
			imv.setFitWidth(size);
			imv.setFitHeight(size);
			imv.getStyleClass().add("nav-icon");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return imv;
	}
}