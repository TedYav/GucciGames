package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad.util.reflection.Reflection;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ConditionPane extends GridPane{
	private List<String> attributes = new ArrayList<String>();
	private ISwitchGroovyPane controller;	
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	
	public ConditionPane(String name, ISwitchGroovyPane controller){
		attributes.add("Condition");
		GeneralPane pane = new GeneralPane(attributes, controller, name);
		//TODO: Get Outcomesfrom backend
		List<String> outcomes = new ArrayList<String>();
		String title = "Add Outcome(s) to Conditions";
		String header = "Outcomes";
		pane.init(outcomes, header, title);
		this.getChildren().add(pane);
	}
	

}
