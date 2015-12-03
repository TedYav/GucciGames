package voogasalad_GucciGames.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.text.Font;
import voogasalad_GucciGames.datastructures.ResourceException;

/**
 * Helper class to manage fonts.
 * 
 * Uses Fonts.properties in resource folder as default font configuration location.
 * @author Ted Yavuzkurt
 *
 */
public class FontManager {
	
	private static final String DEFAULTCONFIG = "Fonts";
	private ResourceBundle myConfig;
	
	public FontManager(){
		this(DEFAULTCONFIG);
	}
	
	public FontManager(String config){
		myConfig = ResourceBundle.getBundle(config);
		loadFonts();
	}

	private void loadFonts() {
		System.out.println(myConfig.keySet());
		myConfig.keySet().stream().forEach(s -> Font.loadFont(FontManager.class.getResource(myConfig.getString(s)).toExternalForm(), 20));
	}
	
	public List<String> listFonts(){
		return new ArrayList<String>(myConfig.keySet());
	}
	
	public Font getFont(String name, double size) throws ResourceException{
		if(myConfig.getString("name")==null)
			throw new ResourceException("Error: font " + name + " not found.");
		
		return Font.loadFont(FontManager.class.getResource(myConfig.getString(name)).toExternalForm(), size);
	}
	
//	public static void main(String[] args){
//		System.out.println(new FontManager().listFonts());
//	}
}
