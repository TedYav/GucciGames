package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams;

import java.util.List;

public abstract class AGroovyParams {

	public abstract String getType();

	public abstract String getName();

	public abstract void setName(String name);

	public abstract void setDependencies(List<String> dependencies);

}
