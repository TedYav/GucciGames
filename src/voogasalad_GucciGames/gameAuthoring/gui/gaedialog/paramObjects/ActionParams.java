package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionParams extends AParamsObject{
	
	private Set<String> myRules;
	private Set<String> myCharacteristics;
	
	private String name;
	private String displayName;
	
	public ActionParams(){
		
	}
	
	public void setRules(String rules){
		List<String> items = Arrays.asList(rules.split("\\s*,\\s*"));
		myRules = new HashSet<String>(items);
		
	}
	
	public void setCharacteristics(String characteristics){
		List<String> items = Arrays.asList(characteristics.split("\\s*,\\s*"));
		myCharacteristics = new HashSet<String>(items);
	}
	
	public ActionParams(String name){
		this();
		this.name = name;
	}
	
	public void addRule(String ruleName){
		myRules.add(ruleName);
	}
	
	public Set<String> getAllRules(){
		return myRules;
	}
	
	public Set<String> getAllCharacteristics(){
		return myCharacteristics;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
		
	}
	
	public String getDisplayName(){
		return displayName;
	}
	
	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("displayName: " + this.displayName);
		
	}

}
