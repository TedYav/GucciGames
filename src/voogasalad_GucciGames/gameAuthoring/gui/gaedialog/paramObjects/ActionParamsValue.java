package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionParamsValue extends AParams{
	
	private Set<String> myRules;
	private Set<ObjParamValue> myCharacteristics;
	private Set<OutcomeParamValue> myOutcomes;
	
	private String name;
	
	public ActionParamsValue(String name){
		this.name = name;
	}
	

	

	public void addRule(String ruleName){
		myRules.add(ruleName);
	}
	public void addCharacteristics(ObjParamValue characteristics){
		myCharacteristics.add(characteristics);
	}
	
	public void addOutcome(OutcomeParamValue outcomeName){
		myOutcomes.add(outcomeName);
	}
	
	public Set<OutcomeParamValue> getAllOutcomes(){
		return myOutcomes;
	}
	public Set<String> getAllRules(){
		return myRules;
	}
	
	public Set<ObjParamValue> getAllCharacteristics(){
		return myCharacteristics;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;		
	}


	@Override
	public void print() {
		System.out.println("name: " + this.name);
		
	}

}
