package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ActionPane extends GridPane implements IDependencies {

	private List<String> attributes = new ArrayList<String>();

	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";

	private ISwitchGroovyPane controller;
	
	private GActionParams param;
	
	private GeneralPane pane;
	
	public ActionPane(String name, ISwitchGroovyPane controller){
		super();
		
		param = new GActionParams(name);
		attributes.add("Action");
		attributes.add("Request");
		
		this.controller = controller;
		pane = new GeneralPane(attributes,controller, this, name);
		List<String> rules = new ArrayList<String>();
		String title = "Add Rule(s) to Action";
		String header = "Actions";
		pane.init(rules, header, title);
		this.getChildren().add(pane);
		
		
	}
	

	@Override
	public void addDependencies(List<String> dep) {
		param.setRules(dep);	
	}


	@Override
	public void setParams() {
		Map<String, String> data = pane.getUserData();
		param.setAction(data.get("Action"));
		param.setRequest(data.get("Request"));		
	}

}
