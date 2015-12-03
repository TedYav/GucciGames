package voogasalad_GucciGames.datastructures;

import java.util.ResourceBundle;

import javafx.scene.text.Font;

/**
 * Helper class to manage fonts.
 * 
 * Uses Fonts.properties in resource folder as default font configuration location.
 * @author Ted Yavuzkurt
 *
 */
public class FontManager {
	
	private static final String DEFAULTCONFIG = "voogasalad_GucciGames.resources.Fonts";
	private ResourceBundle myConfig;
	
	public FontManager(){
		this(DEFAULTCONFIG);
	}
	
	public FontManager(String config){
		myConfig = ResourceBundle.getBundle(config);
	}
	
	public Font getFont(String name, double size) throws ResourceException{
		if(myConfig.getString("name")==null)
			throw new ResourceException("Error: font " + name + " not found.");
		
		return Font.loadFont(FontManager.class.getResource(myConfig.getString(name)).toExternalForm(), size);
	}
}
