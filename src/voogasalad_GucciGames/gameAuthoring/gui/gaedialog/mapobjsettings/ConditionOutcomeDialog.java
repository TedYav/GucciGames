package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.util.Pair;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class ConditionOutcomeDialog extends AGaeDialog {
	
	private List<String> conditions = new ArrayList<String>();
	private List<String> outcomes = new ArrayList<String>();
	private ConditionOutcomePane pane;
	private MapObjectType type;

	
	public ConditionOutcomeDialog(IDialogGaeController controller, MapObjectType type){
		this.type = type;
		
		controller.getPropertiesInterface().getAllConditions().forEach(p -> {
			conditions.add(p.getName());
		});
		controller.getPropertiesInterface().getAllOutcomes().forEach(p -> {
			outcomes.add(p.getName());
		});
		
		this.pane = new ConditionOutcomePane(controller, conditions, outcomes, type);
		this.getDialogPane().setContent(pane);
		final ButtonType save = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().add(save);
		
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == save) {
		        List<String> conditions = pane.getConditions();
		        ObjParamValue outcomeVal = pane.getOutcomeValue();
		        return new Pair<>(conditions, outcomeVal);
		    }
		    return null;
		});
	
		
	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub
		
	}
	

}
