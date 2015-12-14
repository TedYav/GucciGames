package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class ActionDialog extends AGaeDialog{
	private ActionPane actionPane;
	private IDialogGaeController controller;
	private MapObjectType type;
	private ActionParamsValue actionParamsValue;
	
	public ActionDialog(IDialogGaeController controller, MapObjectType type, 
			ActionParamsValue actionParamsValue){
		actionPane = new ActionPane(controller, type, actionParamsValue);
		this.actionParamsValue = actionParamsValue;
		this.controller = controller;
		this.type = type;
		final ButtonType saveBtn = new ButtonType("Save", ButtonData.NEXT_FORWARD);
		this.getDialogPane().getButtonTypes().add(saveBtn);
		this.getDialogPane().setContent(actionPane);
		this.setResultConverter(dialogButton -> {
			if(dialogButton == saveBtn){
				if (this.actionParamsValue.getName() != null) {
					this.controller.getPropertiesInterface().
					addActionParamValue(actionParamsValue);
					
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
