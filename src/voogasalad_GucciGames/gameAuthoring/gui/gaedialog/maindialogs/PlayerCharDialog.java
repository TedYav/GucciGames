package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.List;

import javafx.util.Callback;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ObjParamPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ObjParamPane2;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class PlayerCharDialog extends AGaeDialog<ObjParamValue>{
	private ObjParamPane objParamPane;
	private ObjParamPane2 objParamPane2; 
	private IDialogGaeController controller;
	private int playerid;
	
	public PlayerCharDialog(ObjParam param, IDialogGaeController controller, int playerid){
		super();
		objParamPane = new ObjParamPane(param);
		this.controller = controller;
		this.playerid = playerid;
		this.getDialogPane().setContent(objParamPane);
		this.getDialogPane().getButtonTypes().add(mySave);
		setSaveAction();
		this.show();
	}
	
	public PlayerCharDialog(List<ObjParam> param, IDialogGaeController controller, int playerid){
		super();
		objParamPane2 = new ObjParamPane2(param);
		this.controller = controller;
		this.playerid = playerid;
		this.getDialogPane().setContent(objParamPane2);
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
    			controller.addPlayerCharacteristic(playerid, objParamValue);
    		}
    		return null;
		});
	}

}
