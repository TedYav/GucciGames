package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionParams extends AParams{
	
	private Set<String> myRules;
	private Set<String> myCharacteristics;
	private Set<String> myOutcomes;
	
	private String name;
	
	public ActionParams(String name){
		this.name = name;
	}
	
	public void setRules(String rules){
		List<String> items = Arrays.asList(rules.split("\\s*,\\s*"));
		myRules = new HashSet<String>(items);
		
	}
	
	public void setCharacteristics(String characteristics){
		List<String> items = Arrays.asList(characteristics.split("\\s*,\\s*"));
		myCharacteristics = new HashSet<String>(items);
	}
	
	public void addRule(String ruleName){
		myRules.add(ruleName);
	}
	public void addCharacteristics(String characteristics){
		myCharacteristics.add(characteristics);
	}
	
	public void addOutcome(String outcomeName){
		myOutcomes.add(outcomeName);
	}
	
	public Set<String> getAllOutcomes(){
		return myOutcomes;
	}
	public Set<String> getAllRules(){
		return myRules;
	}
	
	public Set<String> getAllCharacteristics(){
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
