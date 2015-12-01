package voogasalad.util.cloud.config;

import java.util.ResourceBundle;

public class ConfigLoader {

	private static String PREFIX = "voogasalad.util.cloud.config.";
	private static final String MAINCONFIG = "CloudConfig";
	private static final String INTERNALCONFIG = "InternalConfig";
	
	public static void setPrefix(String prefix){
		PREFIX = prefix;
	}
	
	public static ResourceBundle mainConfig(){
		return ResourceBundle.getBundle(PREFIX + MAINCONFIG);
	}
	
	public static ResourceBundle internalConfig(){
		return ResourceBundle.getBundle(PREFIX + INTERNALCONFIG);
	}
	
}
