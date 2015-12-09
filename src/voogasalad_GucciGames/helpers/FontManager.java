package voogasalad_GucciGames.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
		myConfig.keySet().stream().forEach(s -> getFont(s,72));
	}
	
	public List<String> listFonts(){
		return new ArrayList<String>(myConfig.keySet());
	}
	
	public Font getFont(String name, double size) throws ResourceException{
		if(myConfig.getString(name)==null)
			throw new ResourceException("Error: font " + name + " not found.");
		Font f = Font.font("Verdana");
		try {
			f = Font.loadFont(new FileInputStream(new File("resources/fonts/" + myConfig.getString(name))), size);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResourceException("Error: font " + name + " could not be located on filesystem.");
		}
		return f;
	}
	
	public static void main(String[] args){
		System.out.println(new FontManager().listFonts());
	}
}
