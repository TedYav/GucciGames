package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import javafx.util.Callback;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ObjParamPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;

public class PlayerCharDialog extends AGaeDialog<ObjParamValue>{
	ObjParamPane objParamPane; 
	IDialogGaeController controller;
	
	public PlayerCharDialog(ObjParam param, IDialogGaeController controller){
		super();
		objParamPane = new ObjParamPane(param);
		this.controller = controller;
		this.getDialogPane().setContent(objParamPane);
		this.getDialogPane().getButtonTypes().add(mySave);
		setSaveAction();
		this.show();
	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub
		this.setResultConverter(dialogButton -> {
    		if (dialogButton == mySave) {
    			ObjParamValue objParamValue = objParamPane.getAllInputs();
    			//TODO:
//    			controller.setPlayerObjParamValues(objParamValue);
    		}
    		return null;
		});
	}

}
