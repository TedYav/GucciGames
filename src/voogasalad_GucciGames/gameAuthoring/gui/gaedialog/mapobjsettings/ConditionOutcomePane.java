package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.List;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParam;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConditionOutcomePane extends GridPane {
	
	private TextField selectedOutcome = new TextField();
	
	private Button addOutcome = new Button("Add Outcome");
	
	private Button addCondition = new Button("Add Condition");
	
	
	private DialogTableView tableView;
		
	private DropDownMenuField outcomesMenuField;
	
	private int ownerId;
	
	private IDialogGaeController controller;
	
	public ConditionOutcomePane(IDialogGaeController controller, List<String> conditions, List<String> outcomes, int ownerId){
		super();
		this.ownerId = ownerId;
		this.tableView = new DialogTableView(conditions, "Select Conditions");
		this.outcomesMenuField = new DropDownMenuField(outcomes);
		this.add(selectedOutcome, 0, 0);
		this.add(outcomesMenuField, 1, 0);
		this.add(addOutcome, 2, 0);
		this.add(tableView, 0, 1);

		
	}
	
	private OutcomeParam getOutcome(){
		//ObjParam p = controller.get
		//OutcomeParam p = new OutcomeParam();
	}

}
