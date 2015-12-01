package voogasalad_GucciGames.gameplayer.config;

import java.util.ResourceBundle;

public class Config {

	public static final String BASEPATH = "voogasalad_GucciGames.gameplayer.config.";
	
	public static ResourceBundle load(String path){
		return ResourceBundle.getBundle(BASEPATH + path);
	}
	
}
