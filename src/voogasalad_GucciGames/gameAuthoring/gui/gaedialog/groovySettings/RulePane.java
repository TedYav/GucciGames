package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GRuleParams;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class RulePane extends GridPane implements IDependencies {
	
	private List<String> attributes = new ArrayList<String>();
	private ISwitchGroovyPane controller;	
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	private GRuleParams param;
	private GeneralPane pane;
	
	public RulePane(String name, ISwitchGroovyPane controller){
		param = new GRuleParams(name);
		attributes.add("Rule");
		this.controller = controller;
		pane = new GeneralPane(attributes, controller, this, name);
		List<String> actions = new ArrayList<String>();
		String title = "Add Action(s) to Rule";
		String header = "Actions";
		pane.init(actions, header, title);
		this.getChildren().add(pane);
	}


	@Override
	public void addDependencies(List<String> dep) {
		param.setActions(dep);
		
	}


	@Override
	public void setParams() {
		Map<String, String> data = pane.getUserData();
		param.setRule(data.get("Rule"));
		
	}
	


}
