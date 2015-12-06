package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.util.Pair;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;


public class AddConditionDialog extends javafx.scene.control.Dialog<List<ObjParamValue>>{
	
	private ObjParamVBox objParamPane;
	private OutcomeParamValue outcomeVal;
	private MapObjectType type;
	public AddConditionDialog(ISwitchSettingsPane controller, 
			List<ObjParam> conditions, OutcomeParamValue outcomeVal, MapObjectType type){
		this.outcomeVal = outcomeVal;
		this.type = type;
		final ButtonType save  = new ButtonType("Save", ButtonData.FINISH);
		conditions.forEach(c -> {
			System.out.println("condition name : " + c.getName());
			c.getAllParams().forEach((k,v) -> {
				System.out.println(k +  " " + v);
			});
		});
		List<ObjParamValue> conditionParamValues = new ArrayList<ObjParamValue>();
		objParamPane = new ObjParamVBox(controller , conditions, conditionParamValues, type);
		this.getDialogPane().setContent(objParamPane);
		
		this.getDialogPane().getButtonTypes().addAll(save, ButtonType.CANCEL);
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == save) {
		    	outcomeVal.setConditions(conditionParamValues);
		        
		    }
		    return null;
		});
		
	}
}
