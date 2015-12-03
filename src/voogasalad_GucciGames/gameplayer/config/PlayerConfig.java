package voogasalad_GucciGames.gameplayer.config;

import java.util.ResourceBundle;

/**
 * Small helper class for GamePlayer - loads config files automatically
 * @author Ted Yavuzkurt
 *
 */
public class PlayerConfig {

	public static final String BASEPATH = "voogasalad_GucciGames.gameplayer.config.";
	
	public static ResourceBundle load(String path){
		return ResourceBundle.getBundle(BASEPATH + path);
	}
	
}
