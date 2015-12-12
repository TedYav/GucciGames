package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TableElement;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.GaeDialogHelper;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs.AddConditionToOutcomeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs.OutcomeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs.OutcomeParamsDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class ActionPane extends GridPane {

	private Text title = new Text("Select Actions");
	private ISwitchSettingsPane switchPaneInterface;

	private List<ActionParamsValue> dataList = new ArrayList<ActionParamsValue>();

	private ObservableList<ActionParamsValue> actions;
	private final GaeDialogHelper helper = new GaeDialogHelper();
	private Properties prop;
	private IDialogGaeController controller;
	private TextField textField = new TextField();
	private ComboBox<String> dropDown = new ComboBox<String>();
	private String selected = "";
	private Button addBtn = new Button("Add Action");
	private Button addOutConBtn = new Button("Add Outcome/ Conditions");
	private Button addCharBtn = new Button("Add Characteristics");
	private Button addRuleBtn = new Button("Add Rule");
	private MapObjectType type;
	private ActionParamsValue actionParamsValue;
	private List<ObjParamValue> charParamValues;

	private ObservableList<TableElement> data;

	public ActionPane(ISwitchSettingsPane switchPaneInterface, IDialogGaeController controller, Properties prop,
			MapObjectType type, ActionParamsValue actionParamsValue) {
		super();
		this.charParamValues = new ArrayList<ObjParamValue>();
		this.type = type;
		this.switchPaneInterface = switchPaneInterface;
		this.prop = prop;
		this.controller = controller;
		this.actionParamsValue = actionParamsValue;

		List<String> items = new ArrayList<String>();

		controller.getPropertiesInterface().getAllActions().forEach(e -> {
			items.add(e.getName());
		});
		ObservableList<String> options = FXCollections.observableArrayList(items);
		dropDown.setItems(options);

		textField.setText(selected);
		textField.setDisable(true);
		addActionToAddBtn();
		addActionToNextBtn();
		setLayout();

	}

	private void setLayout() {
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(10);
		this.setHgap(10);
		add(textField, 0, 0);
		add(dropDown, 1, 0);
		add(addBtn, 2, 0);
		add(addOutConBtn, 0, 1);
		add(addCharBtn, 1, 1);
		add(addRuleBtn, 2, 1);
	}

	private void addActionToNextBtn() {
		addOutConBtn.setOnAction(e -> {
			// ConditionOutcomeDialog d = new ConditionOutcomeDialog(controller,
			// switchPaneInterface, type, this.actionParamsValue);
			// d.showAndWait();
			selected = dropDown.getSelectionModel().getSelectedItem();
			this.actionParamsValue.setName(selected);

			List<String> outcomes = new ArrayList<String>();

			controller.getPropertiesInterface().getAllOutcomes().forEach(p -> {
				outcomes.add(p.getName());
			});

			// Dialog to select outcome to add

			OutcomeDialog outcomeDialog = new OutcomeDialog(controller, outcomes, type);
			List<String> outcomeNames = new ArrayList<String>();
			outcomeNames.add(outcomeDialog.showAndWait().get());

			ObjParam selectedOutcomeParam = controller.getPropertiesInterface().getSelectedOutcomes(outcomeNames)
					.get(0);
			System.out.println("Selected outcome: " + selectedOutcomeParam.getName());
			OutcomeParamsDialog outcomeParamsDialog = new OutcomeParamsDialog(selectedOutcomeParam);
			// Dialog to select outcome parameters
			ObjParamValue paramValue = outcomeParamsDialog.showAndWait().get();
			OutcomeParamValue outcomeParamValue = null;

			if (paramValue != null) {
				// If valid parameters are selected, create new
				// outcomeParamValue

				outcomeParamValue = new OutcomeParamValue(selectedOutcomeParam.getName(), type, paramValue);
				// add condition to outcomeParamValue
				AddConditionToOutcomeDialog addConditionDialog = new AddConditionToOutcomeDialog(controller);
				List<ObjParamValue> conditionParamValue = addConditionDialog.showAndWait().get();
				if (conditionParamValue != null) {
					// condition parameters set
					outcomeParamValue.setConditions(conditionParamValue);
					outcomeParamValue.getConditions().forEach(cond -> {
						System.out.println("condition: " + cond.getName());
						cond.getParamValues().forEach((k, v) -> {
							System.out.println("condition k: " + k + "condition v: " + v);
						});
					});
				}
			}
			this.actionParamsValue.addOutcome(outcomeParamValue);

		});

		addCharBtn.setOnAction(e -> {
			// new dialog for characteristics
			AddCharacteristicDialog addCharDialog = new AddCharacteristicDialog(controller, type,
					this.actionParamsValue, this.charParamValues);
			addCharDialog.showAndWait();
			this.charParamValues.forEach(p -> {
				this.actionParamsValue.addCharacteristics(p);
			});

		});

		addRuleBtn.setOnAction(e -> {
			// new dialog for rules
			List<String> rules = new ArrayList<String>();
			controller.getPropertiesInterface().getAllRules().forEach(element -> {
				rules.add(element.getName());
			});
			AddRuleDialog ruleDialog = new AddRuleDialog(rules, this.actionParamsValue);
			ruleDialog.showAndWait();
		});

	}

	private void addActionToAddBtn() {

		addBtn.setOnAction(e -> {
			selected = dropDown.getSelectionModel().getSelectedItem();
			System.out.println("selected action: " + selected);
			this.textField.setText(selected);
			this.actionParamsValue.setName(selected);

		});
	}

}
