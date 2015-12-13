package voogasalad_GucciGames.gameEngine.groovyEngine;

public abstract class AGroovyCustomObject {

	protected String myName;

	public AGroovyCustomObject(String name) {
		this.myName = name;
	}

	public String getName() {
		return this.myName;
	}

	public abstract String getGroovyString();

}
