package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams;

import java.util.ArrayList;
import java.util.List;

public class GRuleParams extends AGroovyParams {
	
	private String name;
	private String  rule;
	private List<String> actions = new ArrayList<String>();
	private final String type = "action";
	
	public GRuleParams(String name){
		this.name = name;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getName() {
		return  this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

}
