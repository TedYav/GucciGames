package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.listelements.MainListView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ActionSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.xml.SAXHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ActionVBox extends VBox {	
	public static final int INDEX = 0;
	
	private Text title = new Text("Select Actions");	
	private DropDownMenuField availableActionsMenu;
	private MainListView listView = new MainListView();
	private ScrollPane scrollPane = new ScrollPane();
	private Button nextBtn;
	private ISwitchSettingsPane switchPaneInterface;
	
	private DialogElements dialogElements;
	
	
	
	
	public ActionVBox(DialogElements dialogElements, 
			ISwitchSettingsPane switchPaneInterface){
		this.dialogElements = dialogElements;
		this.switchPaneInterface = switchPaneInterface;
		//TODO: add prop file
		availableActionsMenu = new DropDownMenuField(dialogElements, "action", "action_items", listView);
		scrollPane.setContent(listView);
		nextBtn = new Button("Save & Next");
		addActionToNextBtn();		
		this.getChildren().addAll(title, availableActionsMenu, scrollPane, nextBtn);
		
	}
	
	private void addActionToNextBtn(){
		nextBtn.setOnAction(e -> {
			Set<String> rules = new HashSet<String>();
			Set<String> chars = new HashSet<String>();
		
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		    try {
		        SAXParser saxParser = saxParserFactory.newSAXParser();

		        ActionSAXHandler handler = new ActionSAXHandler(listView.getAllListItemsName());
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

		});

		
	}
	

}
