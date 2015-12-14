package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class AddCharacteristicDialog extends javafx.scene.control.Dialog<List<ObjParamValue>>
		implements ISwitchSettingsPane {

	private CharacteristicPane pane;

	private ActionParamsValue actionParamsValue;
	private List<ObjParamValue> charParamValues;
	private IDialogGaeController controller;

	public AddCharacteristicDialog(IDialogGaeController controller, MapObjectType type,
			ActionParamsValue actionParamsValue, List<ObjParamValue> charParamValues) {
		this.charParamValues = charParamValues;
		this.controller = controller;
		pane = new CharacteristicPane(this, controller, null, type, charParamValues);
		this.actionParamsValue = actionParamsValue;
		this.getDialogPane().setContent(pane);
		final ButtonType saveBtn = new ButtonType("Save Characteristics", ButtonData.NEXT_FORWARD);
		this.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);
		
		this.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.FINISH) {
				pane.getAllValue().forEach(charParamValue -> {
					actionParamsValue.addCharacteristics(charParamValue);
				});
				return pane.getAllValue();
			} 
			
			if(dialogButton == saveBtn){
				if (this.charParamValues.size() != 0) {
					charParamValues.forEach(e -> {
						this.controller.getPropertiesInterface().addMapObjectCharacteristic(e);
					});
				}
			}
			return null;
		});
	}

	@Override
	public void switchSettingsPane(Object p) {
		this.getDialogPane().setContent((Pane) p);
	}

	@Override
	public Optional<ButtonType> getDialogButtonResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
