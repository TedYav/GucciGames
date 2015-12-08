package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams;

import java.util.HashMap;
import java.util.Map;


public class GCharParam{

	private String name;
	private Map<String, String> params = new HashMap<String, String>();

	public GCharParam(String name){
		this.name = name;
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

	public Map<String, String> getAllParams() {
		return params;
	}

	public String getType() {
		return null;
	}



	public Map<String, String> getParamOrderMap() {
		return params;
	}




}
