package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad.util.reflection.Reflection;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class OutcomePane extends GridPane {
	private List<String> attributes = new ArrayList<String>();
	private ISwitchGroovyPane controller;	
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	
	public OutcomePane(String name, ISwitchGroovyPane controller){
		attributes.add("Outcome");
		
		GeneralPane pane = new GeneralPane(attributes,controller, name);
		List<String> conditions = new ArrayList<String>();
		String title = "Add Condition(s) to Outcomes";
		String header = "Conditions";
		pane.init(conditions, header, title);
		this.getChildren().add(pane);

	}
	

}
