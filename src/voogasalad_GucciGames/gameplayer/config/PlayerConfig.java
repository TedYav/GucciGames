package voogasalad_GucciGames.gameplayer.config;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
	
	public static ResourceBundle globalConfig(){
		return load("GlobalConfig");
	}
	
	/**
	 * Returns the specified parameter in ResourceBundle bundle.
	 * i.e. if I want PlayerColor15 I would call getResourceNumber(bundle, "PlayerColor", 15);
	 * @param bundle
	 * @param prefix
	 * @param number
	 * @return
	 */
	public static String getResourceNumber(ResourceBundle bundle, String prefix, Integer number){
		return bundle.keySet().stream()
				.filter( s -> s.startsWith(prefix))
				.filter( s -> s.endsWith( number.toString() ))
				.map( s -> bundle.getString(s))
				.collect(Collectors.toList()).get(0);
	}
	
}
