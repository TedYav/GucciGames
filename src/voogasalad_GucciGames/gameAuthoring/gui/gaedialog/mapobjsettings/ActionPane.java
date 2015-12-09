 package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TableElement;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.GaeDialogHelper;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ActionSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs.AddConditionToOutcomeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs.OutcomeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs.OutcomeParamsDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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
	
	private ObservableList<TableElement> data;
	
	public ActionPane(ISwitchSettingsPane switchPaneInterface, 
			IDialogGaeController controller,Properties prop, MapObjectType type, ActionParamsValue actionParamsValue){
		super();
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
		addActionToNextBtn();
		addActionToAddBtn();
		setLayout();
		
	}

	
	
	private void setLayout(){
		this.setPadding(new Insets(5,5,5,5));
		this.setVgap(10);
		this.setHgap(10);
		add(textField, 0, 0);
		add(dropDown, 1, 0);
		add(addBtn, 2, 0);
		add(addOutConBtn, 0, 1);
		add(addCharBtn, 1, 1);
		add(addRuleBtn, 2 ,1);
	}

	private void addActionToNextBtn(){				
		addOutConBtn.setOnAction(e -> {
			//ConditionOutcomeDialog d = new ConditionOutcomeDialog(controller, switchPaneInterface, type, this.actionParamsValue);
			List<String> outcomes = new ArrayList<String>();
			controller.getPropertiesInterface().getAllOutcomes().forEach(p -> {
				outcomes.add(p.getName());
			});
			
			OutcomeDialog outcomeDialog = new OutcomeDialog(controller, outcomes, type);
						
			List<String> outcomeNames = new ArrayList<String>();
			outcomeNames.add(outcomeDialog.showAndWait().get());
			
			ObjParam outcomeParam = controller.getPropertiesInterface().getSelectedOutcomes(outcomeNames).get(0);
			OutcomeParamsDialog outcomeParamsDialog = 
					new OutcomeParamsDialog(outcomeParam);
			ObjParamValue paramValue = outcomeParamsDialog.showAndWait().get();
			OutcomeParamValue outcomeParamValue;
			if(paramValue != null){
				outcomeParamValue =
						new OutcomeParamValue(selected, type, outcomeParamsDialog.getResult());
				// add condition to outcomeParamValue
				AddConditionToOutcomeDialog addConditionDialog = new AddConditionToOutcomeDialog(controller);
				addConditionDialog.showAndWait();
				//outcomeParamValue.setConditions(items);
			}
			
			

		});
		
		addCharBtn.setOnAction(e -> {
			//new dialog for characteristics
			AddCharacteristicDialog addCharDialog  = new AddCharacteristicDialog(controller, type, this.actionParamsValue);
			addCharDialog.showAndWait();
		});
		
		addRuleBtn.setOnAction(e -> {
			//new dialog for rules
			List<String> rules = new ArrayList<String>();
			controller.getPropertiesInterface().getAllRules().forEach(element -> {
				rules.add(element.getName());
			});
			AddRuleDialog ruleDialog = new AddRuleDialog(rules, this.actionParamsValue);
			ruleDialog.showAndWait();
		});
	}
	
	private void addActionToAddBtn(){
		
		addBtn.setOnAction(e -> {
			selected = dropDown.getSelectionModel().getSelectedItem();
			System.out.println("selected: " + selected);
			this.textField.setText(selected);
			this.actionParamsValue.setName(selected);

		});
	}

		
	

}
