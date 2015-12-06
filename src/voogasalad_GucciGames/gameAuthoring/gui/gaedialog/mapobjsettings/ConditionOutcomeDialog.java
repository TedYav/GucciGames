package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.util.Pair;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;

public class ConditionOutcomeDialog extends javafx.scene.control.Dialog<Pair<List<String>, ObjParamValue>> {
	
	private List<String> conditions = new ArrayList<String>();
	private List<String> outcomes = new ArrayList<String>();
	private ConditionOutcomePane pane;
	private MapObjectType type;
	private ISwitchSettingsPane switchPaneController;

	
	public ConditionOutcomeDialog(IDialogGaeController controller, 
			ISwitchSettingsPane switchPaneController, MapObjectType type){
		this.type = type;
		this.switchPaneController = switchPaneController;
		
		controller.getPropertiesInterface().getAllConditions().forEach(p -> {
			conditions.add(p.getName());
		});
		controller.getPropertiesInterface().getAllOutcomes().forEach(p -> {
			outcomes.add(p.getName());
		});
		
		this.pane = new ConditionOutcomePane(controller, conditions, outcomes, type);
		this.getDialogPane().setContent(pane);
		final ButtonType save = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().addAll(save, ButtonType.CANCEL);
		
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == save) {
		        List<String> conditions = pane.getConditions();
		        //todo; GET CONDITION NOT WORKING
		        List<ObjParam> condParam = new ArrayList<ObjParam>();
		        		controller.getPropertiesInterface().getSelectedConditions(conditions);
		        for(ObjParam o: condParam){
		        	System.out.println("selected: " + o.getName());
		        }
		       
		        ObjParamValue outcomeVal = pane.getOutcomeValue();
		        AddConditionDialog addConditionDialog = 
		        		new AddConditionDialog(this.switchPaneController, condParam);
		        addConditionDialog.showAndWait();
		        List<ObjParamValue> conditionVal = addConditionDialog.getResult();
		        
		        for(ObjParamValue o: conditionVal){
		        	System.out.println(o.getName());
		        }
		        return new Pair<>(conditions, outcomeVal);
		    }
		    return null;
		});
	
		
	}


}
