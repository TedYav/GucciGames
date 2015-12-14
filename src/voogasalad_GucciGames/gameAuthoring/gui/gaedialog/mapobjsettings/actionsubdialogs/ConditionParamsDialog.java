// This entire file is part of my masterpiece.
// Karen Li (kjl32)

package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.AllObjParamPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;

public class ConditionParamsDialog extends AGaeDialog<List<ObjParamValue>> {

	private AllObjParamPane objParamPane;

	public ConditionParamsDialog(IDialogGaeController controller, List<String> selectedConditions) {
		List<ObjParam> conditionParam = new ArrayList<ObjParam>();
		conditionParam.addAll(controller.getPropertiesInterface().getSelectedConditions(selectedConditions));
		objParamPane = new AllObjParamPane(conditionParam, "Selected Conditions");
		this.getDialogPane().setContent(objParamPane);
		final ButtonType saveBtn = new ButtonType("Save Conditions", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().add(saveBtn);
		this.setResultConverter(dialogButton -> {
			if (saveBtn == dialogButton) {
				if (objParamPane.getCheckAllInputs()) {
					return objParamPane.getAllParam();
				} else {
					this.showAlert("Incomplete Input",
							"Incomplete Input for Condition Parameters. No conditions saved");
				}

			}
			return null;
		});

	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub

	}

}
