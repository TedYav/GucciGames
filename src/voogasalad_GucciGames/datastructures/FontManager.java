package voogasalad_GucciGames.datastructures;

import java.util.ResourceBundle;

import javafx.scene.text.Font;

public class FontManager {
	
	private static ResourceBundle fontConfig = ResourceBundle.getBundle("voogasalad_GucciGames.resources.Fonts");
	
	public FontManager(){
		
	}
	
	public static Font getFont(String name, double size) throws ResourceException{
		if(fontConfig.getString("name")==null)
			throw new ResourceException("Error: font " + name + " not found.");
		
		return Font.loadFont(FontManager.class.getResource(fontConfig.getString(name)).toExternalForm(), size);
	}
}
