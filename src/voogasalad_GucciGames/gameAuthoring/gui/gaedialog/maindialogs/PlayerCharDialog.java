package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ObjParamPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;

public class PlayerCharDialog extends AGaeDialog{
	
	public PlayerCharDialog(ObjParam param){
		super();
		ObjParamPane objParamPane = new ObjParamPane(param);
		this.getDialogPane().setContent(objParamPane);
		this.show();
	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub
		
	}
}
