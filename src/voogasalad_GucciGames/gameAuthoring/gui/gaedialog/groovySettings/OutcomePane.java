package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.AGroovyParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GOutcomeParams;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class OutcomePane extends GridPane implements IDependencies {
	private List<String> attributes = new ArrayList<String>();
	private ISwitchGroovyPane controller;
	private IDialogGaeController gaeController;
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	private GOutcomeParams param;
	private GeneralPane pane;
	public OutcomePane(String name, IDialogGaeController gaeController, ISwitchGroovyPane controller){
		attributes.add("Outcome");
		this.gaeController = gaeController;
		this.param = new GOutcomeParams(name);	
		pane = new GeneralPane(attributes, GroovyType.OUTCOME, controller,null, this, name);
		List<String> conditions = new ArrayList<String>();
		String title = "Add Condition(s) to Outcomes";
		String header = "Conditions";
		pane.init(conditions, header, title);
		this.getChildren().add(pane);

	}
	
	@Override
	public void setParams(){
		Map<String, String> data = pane.getUserData();
		param.setOutcome(data.get("Outcome"));
	}

	@Override
	public void addDependencies(List<String> dep) {
		param.setConditions(dep);		
	}

	@Override
	public AGroovyParams getGroovyParamObject() {
		// TODO Auto-generated method stub
		return param;
	}
	

}
