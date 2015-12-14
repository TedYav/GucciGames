package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs;

import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ObjParamPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;

public class OutcomeParamsDialog extends AGaeDialog<ObjParamValue> {

	private ObjParamPane objParamPane;

	public OutcomeParamsDialog(ObjectParam outcomeParam) {
		objParamPane = new ObjParamPane(outcomeParam);
		final ButtonType saveBtn = new ButtonType("Save", ButtonData.NEXT_FORWARD);
		this.getDialogPane().getButtonTypes().add(saveBtn);
		Node saveBtnNode = getDialogPane().lookupButton(saveBtn);
		saveBtnNode.setDisable(objParamPane.checkAllInputs());
		this.getDialogPane().setContent(objParamPane);

		this.setResultConverter(dialogButton -> {
			if (dialogButton == saveBtn) {
				if (objParamPane.checkAllInputs()) {
					System.out.println();
					objParamPane.getAllInputs().getMap().forEach((k, v) -> {
						System.out.println("objParam k: " + k + "objParam v: " + v);
					});
					return objParamPane.getAllInputs();
				}
			} else {
				this.showAlert("Incomplete Input for Outcome", "No outcome saved");
			}
			return null;
		});

	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub

	}

}
