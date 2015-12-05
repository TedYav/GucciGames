package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad.util.reflection.Reflection;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class RulePane extends GridPane{
	
	private List<String> attributes = new ArrayList<String>();
	private ISwitchGroovyPane controller;	
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";

	
	public RulePane(String name, ISwitchGroovyPane controller){
		attributes.add("Rules");
		this.controller = controller;
		GeneralPane pane = new GeneralPane(attributes,controller, name);
		List<String> actions = new ArrayList<String>();
		String title = "Add Action(s) to Rule";
		String header = "Actions";
		pane.init(actions, header, title);
		this.getChildren().add(pane);
	}
	


}
