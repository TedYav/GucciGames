package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ActionSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ActionPane extends GridPane {	
	
	private Text title = new Text("Select Actions");	
	
	private HBox hbox;
	private DropDownMenuField availableActionsMenu;
	private TextField actionText;
	private Button addBtn;
	private Button deleteBtn;
	
	private TableView tableView;
	private TableColumn actionCol;
	
	private ISwitchSettingsPane switchPaneInterface;	
	private DialogElements dialogElements;
	
	private List<ActionParams> dataList = new ArrayList<ActionParams>();
	private ObservableList<ActionParams> actions;
	
	public ActionPane(DialogElements dialogElements, ISwitchSettingsPane switchPaneInterface){
		this.dialogElements = dialogElements;
		this.switchPaneInterface = switchPaneInterface;	
		addElementHBox();
		setTableView();
		//addActionToNextBtn();		
		setPaneElements();
		
	}
	
	private HBox addElementHBox(){
		this.hbox = new HBox(5);
		hbox.setPadding(new Insets(5 ,5 ,5 ,5));
		this.availableActionsMenu = new DropDownMenuField(dialogElements, "action", "action_items");
		this.actionText = new TextField();
		this.addBtn = new Button("Add");
		this.deleteBtn = new Button("Remove Selected");
		hbox.getChildren().addAll(actionText, availableActionsMenu, addBtn, deleteBtn);
		return hbox;
	}
	private void setPaneElements(){
		this.add(title, 0, 0);
		this.add(tableView, 0, 1);
		this.add(hbox, 0, 2);
	}
	private void setTableView(){
		this.tableView = new TableView();
		actionCol = new TableColumn("Action");
		actionCol.setCellValueFactory(
				new PropertyValueFactory<ActionParams, String>("displayName"));
		tableView.getColumns().add(actionCol);
	}
	private void addActionToNextBtn(){
		ButtonType next = new ButtonType("Next");
		switchPaneInterface.setControlBtn(next);
		Optional<ButtonType> result = switchPaneInterface.getDialogButtonResponse();
		/*
		if(result.get() == next) {
			Set<String> rules = new HashSet<String>();
			Set<String> chars = new HashSet<String>();
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			
		    try {
		        SAXParser saxParser = saxParserFactory.newSAXParser();
		        Set<String> columnData = new HashSet<String>();
		        for(Object p:  tableView.getItems() ){
		        	columnData.add(((ActionParams) p).getName());
		        	//columnData.add(((ActionParams)this.actionCol.getColumns()).getName());
		        }

		        ActionSAXHandler handler = new ActionSAXHandler(columnData);
		        saxParser.parse(new 
		        		File("src/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/mapobjectsettings/xml/actionDependencies.xml"), 
		        		handler);
		        List<ActionParams> actionParams = handler.getActionParams();
		        for(ActionParams actionParam : actionParams){
		  
		  
		        	rules.addAll(actionParam.getAllRules());
		        	chars.addAll(actionParam.getAllCharacteristics());
		        }

		        for(String s: rules){
		        	System.out.println("rules: " + s);
		        }
		        
		        for(String s: chars){
		        	System.out.println("chars: " + s);
		        }
		        
		           
		    } catch (ParserConfigurationException | SAXException | IOException ex) {
		        ex.printStackTrace();
		    }
		    
		    switchPaneInterface.switchSettingsPane(new RulesAndCharVBox(rules, chars));
		}
		
		*/
	}
	

}
