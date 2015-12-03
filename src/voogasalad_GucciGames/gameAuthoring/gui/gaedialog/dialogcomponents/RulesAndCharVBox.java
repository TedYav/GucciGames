package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.listelements.CharacteristicsListItem;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.listelements.MainListView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.listelements.RuleListItem;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.CharacteristicsSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.RulesSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.CharacteristicsParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RulesAndCharVBox extends VBox {
	public static final int INDEX = 1;
	
	private Text title = new Text("Rules and Characteristics");
	private MainListView rulesListView = new MainListView();
	private ScrollPane rulesScrollPane = new ScrollPane();
	private MainListView characteristicsListView = new MainListView();
	private ScrollPane characteristicsScrollPane = new ScrollPane();
	private Button saveBtn = new Button("Save");
	private List<CharacteristicsParam> charParams = new ArrayList<CharacteristicsParam>();
	
	private Set<String> rules = new HashSet<String>();
	private Set<String> characteristics = new HashSet<String>();
	
	public RulesAndCharVBox(Set<String> rules, Set<String> characteristics){
		this.rules = rules;
		this.characteristics = characteristics;
		loadCharacteristics();
		characteristicsScrollPane.setContent(characteristicsListView);
		loadRules();
		rulesScrollPane.setContent(rulesListView);
		addActionToSaveBtn();
		this.getChildren().addAll(characteristicsScrollPane, rulesScrollPane, saveBtn);
		
	}
	
	private void loadRules(){
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	 
	        RulesSAXHandler handler = new RulesSAXHandler(rules);
	        saxParser.parse(new 
	        		File("src/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/mapobjectsettings/xml/ruleDependencies.xml"), 
	        		handler);
	        List<RuleParams> rulesParams = handler.getRuleParams();
	        for(RuleParams param : rulesParams){     	
	        	RuleListItem r = new RuleListItem(param.getDisplayName());
	        	rulesListView.addListItem(r);
	        }
	           
	    } catch (ParserConfigurationException | SAXException | IOException ex) {
	        ex.printStackTrace();
	    }
		
	}
	
	private void loadCharacteristics(){
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	 
	        CharacteristicsSAXHandler handler = new CharacteristicsSAXHandler(characteristics);
	        saxParser.parse(new 
	        		File("src/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/mapobjectsettings/xml/characteristicsDependencies.xml"), 
	        		handler);
	        List<CharacteristicsParam> charParams = handler.getCharParams();
	        for(CharacteristicsParam param : charParams){
	        	
	        	CharacteristicsListItem c = new CharacteristicsListItem(param.getDisplayName(), param.getName(), param.getMin(), param.getMax(), 1);
	        	characteristicsListView.addListItem(c);
	        }
	           
	    } catch (ParserConfigurationException | SAXException | IOException ex) {
	        ex.printStackTrace();
	    }
		
	}
	
	private void addActionToSaveBtn(){
		this.saveBtn.setOnAction(e -> {
			
		});
	}

}
