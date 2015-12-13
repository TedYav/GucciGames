package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.AGroovyParams;

public interface IDependencies {

	public void addDependencies(List<String> dep);

	public void setParams();

	public AGroovyParams getGroovyParamObject();

}
