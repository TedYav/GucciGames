package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.layout.GridPane;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.AGroovyParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GRuleParams;

public class RulePane extends GridPane implements IDependencies {

	private List<String> attributes = new ArrayList<String>();
	private ISwitchGroovyPane controller;
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	private GRuleParams param;
	private GeneralPane pane;
	private IDialogGaeController gaeController;

	public RulePane(String name, IDialogGaeController gaeController, ISwitchGroovyPane controller) {
		param = new GRuleParams(name);
		attributes.add("Rule");
		this.controller = controller;
		this.gaeController = gaeController;
		pane = new GeneralPane(attributes, GroovyType.RULE, controller, gaeController, this, name);
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

	@Override
	public AGroovyParams getGroovyParamObject() {

		return param;
	}

}
