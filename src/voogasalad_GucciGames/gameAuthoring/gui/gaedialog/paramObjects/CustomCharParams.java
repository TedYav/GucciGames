package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.HashMap;
import java.util.Map;

public class CustomCharParams {
	
	private String name;
	private Map<String, String> map = new HashMap<String, String>();
	
	public CustomCharParams(String name){
		this.name = name;
	}
	
	public void addParam(String type, String name){
		map.put(type, name);
	}
	
	public Map<String, String> getAllParams(){
		return map;
	}
	
	public String getName(){
		return this.name;
	}
	

}
