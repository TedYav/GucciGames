package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GConditionParams;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ConditionPane extends GridPane implements IDependencies{
	private List<String> attributes = new ArrayList<String>();
	private ISwitchGroovyPane controller;
	private GConditionParams params;
	private GeneralPane pane;
	
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	
	public ConditionPane(String name, ISwitchGroovyPane controller){
		attributes.add("Condition");
		params = new GConditionParams(name);
		pane = new GeneralPane(attributes, controller, this, name);
		//TODO: Get Outcomes from backend
		List<String> outcomes = new ArrayList<String>();
		String title = "Add Outcome(s) to Conditions";
		String header = "Outcomes";
		pane.init(outcomes, header, title);
		this.getChildren().add(pane);
	}

	@Override
	public void addDependencies(List<String> dep) {
		params.setOutcomes(dep);		
	}

	@Override
	public void setParams() {
		Map<String, String> data = pane.getUserData();
		params.setCondition(data.get("Condition"));	
	}
	

}
