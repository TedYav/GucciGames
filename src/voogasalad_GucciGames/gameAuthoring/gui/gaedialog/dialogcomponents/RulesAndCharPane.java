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

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.CharacteristicsSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.RulesSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.CharacteristicsParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RulesAndCharPane extends GridPane{
	
	private Text title = new Text("Rules and Characteristics");
	private ScrollPane rulesScrollPane = new ScrollPane();
	private ScrollPane characteristicsScrollPane = new ScrollPane();
	private Button saveBtn = new Button("Save");
	private List<CharacteristicsParam> charParams = new ArrayList<CharacteristicsParam>();
	
	private Set<String> rules = new HashSet<String>();
	private Set<String> characteristics = new HashSet<String>();
	
	private DialogTableView rulesTableView;
	
	public RulesAndCharPane(Set<String> rules, Set<String> characteristics){
		this.rules = rules;
		this.characteristics = characteristics;
		loadCharacteristics();
		loadRules();
		
		addActionToSaveBtn();
		
	}
	
	private void loadRules(){
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		List<RuleParams> rulesParams = new ArrayList<RuleParams>();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	 
	        RulesSAXHandler handler = new RulesSAXHandler(rules);
	        saxParser.parse(new 
	        		File("src/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/mapobjectsettings/xml/ruleDependencies.xml"), 
	        		handler);
	        rulesParams = handler.getRuleParams();	       
	          
	    } catch (ParserConfigurationException | SAXException | IOException ex) {
	        ex.printStackTrace();
	    }
	    List<TableElement> tableElements = new ArrayList<TableElement>();
	    rulesParams.stream().forEach(p -> tableElements.add(new TableElement(p.getDisplayName(), "rule")));
	    ObservableList<TableElement> data = FXCollections.observableArrayList(tableElements);
	    this.rulesTableView = new DialogTableView(data, "Rules");
	    this.add(rulesTableView, 0, 1);
		
	}
	
	private void loadCharacteristics(){
		VBox charVBox = new VBox(5);
		charVBox.setPadding(new Insets(5,5,5,5));
		Label label = new Label("Characteristics");
		label.setFont(new Font("Arial", 20));
		charVBox.getChildren().add(label);
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	 
	        CharacteristicsSAXHandler handler = new CharacteristicsSAXHandler(characteristics);
	        saxParser.parse(new 
	        		File("src/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/mapobjectsettings/xml/characteristicsDependencies.xml"), 
	        		handler);
	        List<CharacteristicsParam> charParams = handler.getCharParams();
	        for(CharacteristicsParam param : charParams){
	        	CharGridPane p = new CharGridPane(param);
	        	Label labelChar = new Label(param.getName());
	        	charVBox.getChildren().add(labelChar);
	        	charVBox.getChildren().add(p);
	        }
	           
	    } catch (ParserConfigurationException | SAXException | IOException ex) {
	        ex.printStackTrace();
	    }
	    this.add(charVBox, 1, 1);
		
	}
	
	private void addActionToSaveBtn(){
		this.saveBtn.setOnAction(e -> {
			
		});
	}

}
