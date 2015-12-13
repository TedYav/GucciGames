package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class ConditionOutcomePane extends GridPane {

	private Label title = new Label("Add an Outcome to Action");

	private TextField selectedOutcome = new TextField();

	private Button addOutcome = new Button("Add Outcome");

	private DialogTableView tableView;

	private DropDownMenuField outcomesMenuField;

	private MapObjectType type;

	private IDialogGaeController controller;

	private List<String> selectedOutcomes = new ArrayList<String>();
	
	List<ObjParam> outcomeParamList = new ArrayList<>();
	
	ObjParamListPane objParamListPane;

//	private ObjParam outcomeParam;

//	private ObjParamPane objParamPane;

	public ConditionOutcomePane(IDialogGaeController controller, List<String> conditions, List<String> outcomes,
			MapObjectType type) {
		super();
		this.title.setFont(new Font("Arial", 20));
		this.title.setMinWidth(200);
		this.type = type;
		this.tableView = new DialogTableView(conditions, "Select Conditions");
		this.outcomesMenuField = new DropDownMenuField(outcomes);
		this.selectedOutcome.setDisable(true);
		this.controller = controller;

		this.getChildren().add(title);
		this.add(selectedOutcome, 1, 1);
		this.add(outcomesMenuField, 2, 1);
		this.add(addOutcome, 3, 1);
		this.add(tableView, 1, 2);
		setActionForAddCondition();

	}

	private void setActionForAddCondition() {
		addOutcome.setOnAction(e -> {
			String selected = this.outcomesMenuField.getSelected();
			System.out.println(selected);
			this.selectedOutcome.setText(selected);
			List<String> selectedOutcomes = new ArrayList<String>();
			selectedOutcomes.add(selected);
			List<ObjParam> outcomes = controller.getPropertiesInterface().getSelectedOutcomes(selectedOutcomes);
			System.out.println("outcome size: " + outcomes.size());
			outcomeParamList = controller.getPropertiesInterface().getSelectedOutcomes(selectedOutcomes);
			this.setOutcomeParam();
		});
	}

	private void setOutcomeParam() {
		if (this.outcomeParamList != null) {
			this.getChildren().remove(objParamListPane);
			objParamListPane = new ObjParamListPane(outcomeParamList);
			this.add(objParamListPane, 3, 2);
		}
	}

	protected OutcomeParamValue getOutcomeValue() {
		OutcomeParamValue outcomeParam = null;
		if (objParamListPane != null) {
			List<ObjParamValue> objParamValues = objParamListPane.getAllInputsList();
			//TODO: check this for loop
//			for(ObjParamValue eachObjParamValue : objParamValues){
				outcomeParam = new OutcomeParamValue(selectedOutcome.getText(), type, objParamValues.get(0));
//			}
		}
		return outcomeParam;

	}

	protected List<String> getConditions() {
		return tableView.getData();
	}

}
