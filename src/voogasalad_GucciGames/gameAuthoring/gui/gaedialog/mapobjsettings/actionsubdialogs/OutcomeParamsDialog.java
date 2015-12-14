// This entire file is part of my masterpiece.
// Karen Li (kjl32)

package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ObjParamListPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;

public class OutcomeParamsDialog extends AGaeDialog<List<ObjParamValue>> {

	private ObjParamListPane objParamListPane;

	public OutcomeParamsDialog(List<ObjParam> outcomeParam) {
		objParamListPane = new ObjParamListPane(outcomeParam);
		final ButtonType saveBtn = new ButtonType("Save", ButtonData.NEXT_FORWARD);
		this.getDialogPane().getButtonTypes().add(saveBtn);
		Node saveBtnNode = getDialogPane().lookupButton(saveBtn);
		saveBtnNode.setDisable(objParamListPane.checkAllInputs());
		this.getDialogPane().setContent(objParamListPane);

		this.setResultConverter(dialogButton -> {
			if (dialogButton == saveBtn) {
				if (objParamListPane.checkAllInputs()) {
					System.out.println();
					List<ObjParamValue> objParamValueList = objParamListPane.getAllInputsList();
					for(ObjParamValue eachObjParamValue : objParamValueList){
						eachObjParamValue.getMap().forEach((k, v) -> {
							System.out.println("objParam k: " + k + "objParam v: " + v);
						});
					}
					return objParamListPane.getAllInputsList();
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
