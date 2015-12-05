package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CharacteristicsParam {

	private String name;
	private String displayName;
	private Map<String, String> params = new HashMap<String, String>();

	
	public CharacteristicsParam(String name){
		this.setName(name);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addParam(String type, String name){
		params.put(name, type);
	}
	
	public void removeParam(String name){
		params.remove(name);
	}
	
	public Map<String, String> getAllParams(){
		return params;
	}

	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public void print(){
		System.out.println("display name:" + this.displayName);
	}

}
