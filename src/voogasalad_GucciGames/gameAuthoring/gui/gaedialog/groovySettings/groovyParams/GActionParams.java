package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams;

import java.util.ArrayList;
import java.util.List;

public class GActionParams extends AGroovyParams {
	
	private String name;
	private String action;
	private String request;
	private List<String> rules = new ArrayList<String>();
	private final String type = "action";
	
	public GActionParams(String name){
		this.setName(name);
	}

	public List<String> getRules() {
		return rules;
	}

	public void setRules(List<String> rules) {
		this.rules = rules;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		System.out.println("request: " + request);

		this.request = request;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	

	
	

}
