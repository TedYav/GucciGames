package voogasalad.util.cloud.data;

import voogasalad.util.cloud.config.ConfigLoader;

public enum CloudVariable {

	PROCESS(ConfigLoader.internalConfig().getString("process")), 
	ADD(ConfigLoader.internalConfig().getString("add")), 
	GAMENAME(ConfigLoader.internalConfig().getString("gamename")), 
	PLAYERNAME(ConfigLoader.internalConfig().getString("playername")), 
	SCORE(ConfigLoader.internalConfig().getString("score")), 
	TITLE(ConfigLoader.internalConfig().getString("title")), 
	ACTION(ConfigLoader.internalConfig().getString("action")), 
	HIGHSCORE(ConfigLoader.internalConfig().getString("highscore")), 
	NAME(ConfigLoader.internalConfig().getString("name")), 
	REGISTER(ConfigLoader.internalConfig().getString("register")), 
	RETRIEVE(ConfigLoader.internalConfig().getString("retrieve"));

	private String myValue;

	private CloudVariable(String value) {
		myValue = value;
	}

	public String getValue() {
		return myValue;
	}

}
