package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;

public class ConditionOutcomeDialog extends AGaeDialog {
	
	private List<String> conditions = new ArrayList<String>();
	private List<String> outcomes = new ArrayList<String>();
	private ConditionOutcomePane pane;
	
	public ConditionOutcomeDialog(IDialogGaeController controller){
		
//		controller.getAllConditions().forEach(p -> {
//			conditions.add(p.getName());
//		});
//		controller.getAllOutcomes().forEach(p -> {
//			outcomes.add(p.getName());
//		});
		
		this.pane = new ConditionOutcomePane(conditions, outcomes);
		this.getDialogPane().setContent(pane);
		final ButtonType save = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().add(save);
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == save) {
		        //return new Pair<>(username.getText(), password.getText());
		    }
		    return null;
		});
		
	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub
		
	}
	

}
