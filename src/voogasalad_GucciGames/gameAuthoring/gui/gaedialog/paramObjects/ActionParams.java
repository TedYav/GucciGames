package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.HashSet;
import java.util.Set;

public class ActionParams extends AParamsObject{
	
	private Set<String> myRules;
	
	private String name;
	
	public ActionParams(String name){
		this.name = name;
		myRules= new HashSet<String>();
	}
	
	public void addRule(String ruleName){
		myRules.add(ruleName);
	}
	
	public Set<String> getAllRules(){
		return myRules;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String structureName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

}
