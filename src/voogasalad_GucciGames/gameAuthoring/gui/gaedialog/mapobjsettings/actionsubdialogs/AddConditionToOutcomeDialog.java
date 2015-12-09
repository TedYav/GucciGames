package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;

public class AddConditionToOutcomeDialog extends AGaeDialog<List<String>>{
	
	private DialogTableView tableView;
	
	public AddConditionToOutcomeDialog(IDialogGaeController controller){
		List<String> conditions = new ArrayList<String>();
		controller.getPropertiesInterface().getAllConditions().forEach(condition -> {
			conditions.add(condition.getName());
		});
		
		this.tableView = new DialogTableView(conditions, "Select Conditions");
		this.getDialogPane().setContent(tableView);
		final ButtonType saveBtn = new ButtonType("Save Conditions & Next", 
				ButtonData.NEXT_FORWARD);
		this.getDialogPane().getButtonTypes().add(saveBtn);
		this.setResultConverter(dialogButton -> {
			if(dialogButton == saveBtn){
				ConditionParamsDialog conditionParamsDialog = 
						new ConditionParamsDialog(controller, this.tableView.getData());
				conditionParamsDialog.showAndWait();		
			}
			return null;
				
		});
		
	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub
		
	}
	

}
