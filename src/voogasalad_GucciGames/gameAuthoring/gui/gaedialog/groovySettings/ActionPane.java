package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.layout.GridPane;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.AGroovyParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;

public class ActionPane extends GridPane implements IDependencies {

	private List<String> attributes = new ArrayList<String>();

	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";

	private ISwitchGroovyPane controller;
	
	private GActionParams param;
	
	private GeneralPane pane;
	
	private IDialogGaeController gaeController;
	
	public ActionPane(String name, ISwitchGroovyPane controller, IDialogGaeController gaeController){
		super();
		this.gaeController = gaeController;
		
		param = new GActionParams(name);
		attributes.add("Action");
		attributes.add("Request");
		
		this.controller = controller;
		pane = new GeneralPane(attributes, GroovyType.ACTION, controller, gaeController, this, name);
		List<String> rules = new ArrayList<String>();
		gaeController.getPropertiesInterface().getAllRules().forEach(rule -> {
			rules.add(rule.getName());
		});
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


	@Override
	public AGroovyParams getGroovyParamObject() {
		// TODO Auto-generated method stub
		return param;
	}

}
