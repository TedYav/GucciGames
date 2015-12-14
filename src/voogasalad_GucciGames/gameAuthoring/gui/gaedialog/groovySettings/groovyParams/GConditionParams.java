package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams;

import java.util.ArrayList;
import java.util.List;

public class GConditionParams extends AGroovyParams {

	private String name;
	private String condition;
	private List<String> outcomes = new ArrayList<String>();
	private final String type = "condition";

	public GConditionParams(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<String> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(List<String> outcomes) {
		this.outcomes = outcomes;
	}

	@Override
	public void setDependencies(List<String> dependencies) {
		outcomes.addAll(dependencies);

	}

}
