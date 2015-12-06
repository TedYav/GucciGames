package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.List;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.util.Pair;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;

public class AddConditionDialog extends javafx.scene.control.Dialog<List<ObjParamValue>>{
	
	private ObjParamVBox objParamPane;
	public AddConditionDialog(ISwitchSettingsPane controller, List<ObjParam> conditions){
		
		final ButtonType save  = new ButtonType("Save", ButtonData.FINISH);
		conditions.forEach(c -> {
			System.out.println(c.getName());
		});
		objParamPane = new ObjParamVBox(controller , conditions);
		this.getDialogPane().setContent(objParamPane);
		
		this.getDialogPane().getButtonTypes().addAll(save, ButtonType.CANCEL);
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == save) {
		        return objParamPane.getAllParam();
		    }
		    return null;
		});
		
	}
}
