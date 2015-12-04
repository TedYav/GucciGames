package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.scene.control.Dialog;


public class GaeDialogHelper extends javafx.scene.control.Dialog {

	public Properties loadProperties(String path){
		Properties prop = new Properties();
		InputStream input = getClass().getResourceAsStream(path);
		if(input == null){
			System.out.println("is null");
		}
		try {
			prop.load(input);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;		
	}


}
