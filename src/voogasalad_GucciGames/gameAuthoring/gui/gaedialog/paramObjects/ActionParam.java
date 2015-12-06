package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionParam {
	
	private List<String> myRules = new ArrayList<String>();
	private List<String> myCharacteristics;
	String name;
	
	public ActionParam(String name){
		this.name = name;
	}
	
	public void setRules(String rules){
		List<String> items = Arrays.asList(rules.split("\\s*,\\s*"));
		myRules.addAll(items);
		
	}
	
	public void setCharacteristics(String characteristics){
		List<String> items = Arrays.asList(characteristics.split("\\s*,\\s*"));
		myCharacteristics.addAll(myRules);
		
	}

}
