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
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
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
	Button addOutConBtn = new Button("Add Outcome/ Conditions");
	Button addCharBtn = new Button("Add Characteristics");
	private MapObjectType type;
	
	private ObservableList<TableElement> data;
	
	public ActionPane(ISwitchSettingsPane switchPaneInterface, 
			IDialogGaeController controller,Properties prop, MapObjectType type){
		super();
		this.type = type;
		this.switchPaneInterface = switchPaneInterface;	
		this.prop = prop;
		this.controller = controller;
		
		//TODO: get all actions
		
		List<String> items = helper.parseStringToList(prop, 
				"action_items");
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
		add(addOutConBtn, 3, 6);
		add(addCharBtn, 4, 6);
	}

	private void addActionToNextBtn(){				
		addOutConBtn.setOnAction(e -> {
			//TODO: show add rules
		    //switchPaneInterface.switchSettingsPane(new RulesAndCharPane(rules, chars));
			ConditionOutcomeDialog d = new ConditionOutcomeDialog(controller, switchPaneInterface, type);
			d.showAndWait();

		});
		
		addCharBtn.setOnAction(e -> {
			//new dialog for characteristics
			AddCharacteristicDialog addCharDialog  = new AddCharacteristicDialog(controller, type);
			addCharDialog.showAndWait();
		});
	}
	
	private void addActionToAddBtn(){
		
		addBtn.setOnAction(e -> {
			selected = dropDown.getSelectionModel().getSelectedItem();
			System.out.println("selected: " + selected);
			this.textField.setText(selected);
		});
	}

		
	

}
