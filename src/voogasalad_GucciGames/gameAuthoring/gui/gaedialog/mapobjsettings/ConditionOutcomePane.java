package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ConditionOutcomePane extends GridPane {
	
	private Label title  = new Label("Add an Outcome to Action");
	
	private TextField selectedOutcome = new TextField();
	
	private Button addOutcome = new Button("Add Outcome");
		
	
	private DialogTableView tableView;
		
	private DropDownMenuField outcomesMenuField;
	
	private MapObjectType type;
	
	private IDialogGaeController controller;
	
	private List<String> selectedOutcomes = new ArrayList<String>();
	
	private ObjParam outcomeParam;
	
	private ObjParamPane objParamPane;
	
	public ConditionOutcomePane(IDialogGaeController controller, List<String> conditions, List<String> outcomes, MapObjectType type){
		super();
		this.title.setFont(new Font("Arial", 20));
		this.type = type;
		this.tableView = new DialogTableView(conditions, "Select Conditions");
		this.outcomesMenuField = new DropDownMenuField(outcomes);
		this.selectedOutcome.setDisable(true);
		this.controller = controller;
		this.add(selectedOutcome, 0, 0);
		this.add(outcomesMenuField, 1, 0);
		this.add(addOutcome, 2, 0);
		this.add(tableView, 0, 1);
		setActionForAddCondition();

		
	}
	
	private void setActionForAddCondition(){
		addOutcome.setOnAction(e -> {
			String selected = this.outcomesMenuField.getSelected();
			System.out.println(selected);
			this.selectedOutcome.setText(selected);
			List<String> selectedOutcomes = new ArrayList<String>();
			selectedOutcomes.add(selected);
			List<ObjParam> outcomes = controller.getPropertiesInterface().getSelectedOutcomes(selectedOutcomes);
			System.out.println("outcome size: " + outcomes.size());
			outcomeParam = controller.getPropertiesInterface().getSelectedOutcomes(selectedOutcomes).get(0);
			this.setOutcomeParam();
		});
	}
	
	private void setOutcomeParam(){
		if(this.outcomeParam != null){
			this.getChildren().remove(objParamPane);
			objParamPane  = new ObjParamPane(outcomeParam);
			this.add(objParamPane, 1, 1);
			
		}
	}
	
	protected OutcomeParamValue getOutcomeValue(){
		OutcomeParamValue outcomeParam = null;
		if(objParamPane != null){
			
			outcomeParam = new OutcomeParamValue(selectedOutcome.getText(), type, objParamPane.getAllInputs());

		}
		return outcomeParam;
		
		
	}
	
	protected List<String> getConditions(){
		return tableView.getData();
	}
	
	
}
