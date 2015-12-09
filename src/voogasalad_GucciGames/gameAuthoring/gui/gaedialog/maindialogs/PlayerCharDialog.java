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
		objParamListPane = new ObjParamListPane(param, playerid);
		this.controller = controller;
		this.playerid = playerid;
		this.getDialogPane().setContent(objParamListPane);
		this.getDialogPane().getButtonTypes().add(mySave);
		setSaveAction();
		this.showAndWait();
	}

	@Override
	protected void setSaveAction() {
		this.setResultConverter(dialogButton -> {
    		if (dialogButton == mySave) {
    			List<ObjParamValue> objParamValues = objParamListPane.getAllInputsList();
    			for(int i=0; i<objParamValues.size(); i++){
    				System.out.println(playerid);
    				System.out.println(objParamValues.get(i).getName());
        			controller.getPropertiesInterface().addPlayerCharacteristic(playerid, objParamValues.get(i));
    			}
    		}
    		return null;
		});
	}
	
	

}
