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

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.GaeDialogHelper;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ActionSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ActionPane extends GridPane {	
	
	private Text title = new Text("Select Actions");		
	private ISwitchSettingsPane switchPaneInterface;	
	
	private List<ActionParams> dataList = new ArrayList<ActionParams>();
	private ObservableList<ActionParams> data;
	
	private ObservableList<ActionParams> actions;
	private final GaeDialogHelper helper = new GaeDialogHelper();
	private DialogTableView tableView ;
	private Properties prop;
	
	public ActionPane(ISwitchSettingsPane switchPaneInterface, Properties prop){
		super();
		this.switchPaneInterface = switchPaneInterface;	
		this.prop = prop;
		data = FXCollections.observableArrayList(dataList);
		
		List<String> items = helper.parseStringToList(prop, 
				"action_items");
		tableView = new DialogTableView(items, "Actions");
		this.add(tableView, 0, 0);
		addActionToNextBtn();
		
	}
	

	private void addActionToNextBtn(){
		Button nextBtn = new Button("Next");
		this.add(nextBtn, 1, 1);
		nextBtn.setOnAction(e -> {
			Set<String> rules = new HashSet<String>();
			Set<String> chars = new HashSet<String>();
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			/*
		    try {
		        SAXParser saxParser = saxParserFactory.newSAXParser();

		        ActionSAXHandler handler = new ActionSAXHandler(
		        		new HashSet<String>(tableView.getData()));		        
		       
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
		    */
		    switchPaneInterface.switchSettingsPane(new RulesAndCharPane(rules, chars));
		    
	
		});
}
		
	

}
