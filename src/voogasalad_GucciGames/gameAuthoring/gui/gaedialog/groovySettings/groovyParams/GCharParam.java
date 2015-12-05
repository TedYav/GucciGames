package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GCharParam extends AGroovyParams{

	private String name;
	private String displayName;
	private Map<String, String> params = new HashMap<String, String>();
	private final String type = "Characteristic";

	
	public GCharParam(String name){
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

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

}
