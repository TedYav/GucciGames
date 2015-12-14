package voogasalad_GucciGames.gameEngine.groovyEngine;

import java.util.List;
import java.util.ResourceBundle;

public class GameGroovyEngine {

	public static final ResourceBundle RESOURCE = ResourceBundle
			.getBundle("resources.groovy.groovyStringsMapObjectCharacteristic");

	public GroovyLoader createLoader(List<AGroovyCustomObject> bundle) {
		// TODO Auto-generated method stub
		GroovyLoader loader = new GroovyLoader();
		for (AGroovyCustomObject b : bundle) {
			loader.add(b.getName(), b.getGroovyString());
		}
		return loader;
	}

}
