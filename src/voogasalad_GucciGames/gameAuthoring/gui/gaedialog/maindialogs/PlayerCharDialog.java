package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.List;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ObjParamListPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;

public class PlayerCharDialog extends AGaeDialog<ObjParamValue>{
	private ObjParamListPane objParamListPane;
	private IDialogGaeController controller;
	private int playerid;
	
	public PlayerCharDialog(List<ObjParam> param, IDialogGaeController controller, int playerid){
		super();
		objParamListPane = new ObjParamListPane(param);
		this.controller = controller;
		this.playerid = playerid;
		this.getDialogPane().setContent(objParamListPane);
		this.getDialogPane().getButtonTypes().add(mySave);
		setSaveAction();
		this.show();
	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub
		this.setResultConverter(dialogButton -> {
    		if (dialogButton == mySave) {
    			List<ObjParamValue> objParamValues = objParamListPane.getAllInputsList();
    			//TODO:
    			for(int i=0; i<objParamValues.size(); i++){
        			controller.addPlayerCharacteristic(playerid, objParamValues.get(i));
    			}
    		}
    		return null;
		});
	}
	
	

}
