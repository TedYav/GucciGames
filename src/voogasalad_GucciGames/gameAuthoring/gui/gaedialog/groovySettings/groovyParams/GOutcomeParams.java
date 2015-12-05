package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams;

import java.util.ArrayList;
import java.util.List;

public class GOutcomeParams extends AGroovyParams {
	
	private String name;
	private String outcome;
	private List<String> conditions = new ArrayList<String>();
	private final String type = "outcome";
	
	public GOutcomeParams(String name){
		this.name = name;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
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

	public List<String> getConditions() {
		return conditions;
	}

	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

}
