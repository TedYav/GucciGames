package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.util.Pair;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class ConditionOutcomeDialog extends javafx.scene.control.Dialog<Pair<List<String>, ObjParamValue>> {

	private List<String> conditions = new ArrayList<String>();
	private List<String> outcomes = new ArrayList<String>();
	private ConditionOutcomePane pane;
	private MapObjectType type;
	private ISwitchSettingsPane switchPaneController;
	private ActionParamsValue actionParamsValue;

	public ConditionOutcomeDialog(IDialogGaeController controller, ISwitchSettingsPane switchPaneController,
			MapObjectType type, ActionParamsValue actionParamsValue) {
		this.type = type;
		this.switchPaneController = switchPaneController;
		this.actionParamsValue = actionParamsValue;

		controller.getPropertiesInterface().getAllConditions().forEach(p -> {
			conditions.add(p.getName());
		});
		controller.getPropertiesInterface().getAllOutcomes().forEach(p -> {
			outcomes.add(p.getName());
		});

		this.pane = new ConditionOutcomePane(controller, conditions, outcomes, type);
		this.getDialogPane().setContent(pane);
		final ButtonType save = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().addAll(save, ButtonType.CANCEL);

		this.setResultConverter(dialogButton -> {
			if (dialogButton == save) {
				List<String> conditions = this.pane.getConditions();

				List<ObjectParam> condParam = controller.getPropertiesInterface().getSelectedConditions(conditions);

				OutcomeParamValue outcomeVal = pane.getOutcomeValue();

				if (outcomeVal != null) {
					AddConditionDialog addConditionDialog = new AddConditionDialog(this.switchPaneController, condParam,
							outcomeVal, type);
					addConditionDialog.showAndWait();
					this.actionParamsValue.addOutcome(outcomeVal);
				}
			}
			return null;
		});

	}

}
