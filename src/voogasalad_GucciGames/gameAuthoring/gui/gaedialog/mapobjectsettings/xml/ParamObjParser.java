package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TableElement;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ObjParamPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;

public class ParamObjParser {
	
	private Set<ObjParam> allMapObjCharParams = new HashSet<ObjParam>();
	
	private Set<ObjParam> allPlayerCharParams = new HashSet<ObjParam>();
	
	private Set<ObjParam> allOutcomeParams = new HashSet<ObjParam>();
	
	private Set<ObjParam> allConditionParams = new HashSet<ObjParam>();
	
	Set<RuleParams> allRules = new HashSet<RuleParams>();
	
	Set<ActionParams> allActions = new HashSet<ActionParams>();
	
	private static final String mainPath = "src/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/mapobjectsettings/xml/";
	private static final String mapObjCharPath = mainPath + "mapObjCharDependencies.xml";
	private static final String playerCharPath = mainPath + "playerCharDependencies.xml";
	private static final String outcomePath = mainPath + "outcomeDependencies.xml";
	private static final String conditionPath = mainPath + "conditionDependencies.xml";
	private static final String actionPath = mainPath + "actionDependencies.xml";
	private static final String rulePath = mainPath + "ruleDependencies.xml";
	
	public ParamObjParser(){
		parseAll();
	}
	
	public Set<ObjParam> getMapObjChars(){
		return allMapObjCharParams;
	}
	
	public Set<ObjParam> getPlayerChars(){
		return allPlayerCharParams;
	}
	
	public Set<ObjParam> getOutcomes(){
		return allOutcomeParams;
	}
	
	public Set<ObjParam> getConditions(){
		return allConditionParams;
	}
	
	public Set<RuleParams> getRules(){
		return allRules;
	}
	
	public Set<ActionParams> getActions(){
		return allActions;
	}
	
	private void parseAll(){
		parseOutcomes();
		parseMapObjChar();
		parsePlayerChar();
		parseCondition();
		parseRule();
		parseAction();
		
	}
	
	private void parseAction(){
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	 
	        ActionSAXHandler handler = new ActionSAXHandler();
	        saxParser.parse(new 
	        		File(actionPath), 
	        		handler);
	        List<ActionParams> list = handler.getActionParams();	
	        this.allActions = new HashSet<ActionParams>(list);
	         
	    } catch (ParserConfigurationException | SAXException | IOException ex) {
	        ex.printStackTrace();
	    }
		
		
		
	}
	
	private void parseRule(){
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	 
	        RulesSAXHandler handler = new RulesSAXHandler();
	        saxParser.parse(new 
	        		File(rulePath), 
	        		handler);
	        List<RuleParams> list = handler.getRuleParams();	
	        this.allRules = new HashSet<RuleParams>(list);
	         
	    } catch (ParserConfigurationException | SAXException | IOException ex) {
	        ex.printStackTrace();
	    }

		
	}
	
	private void parse(String path, Set<ObjParam> set, ObjType type){
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	 
	        CharacteristicsSAXHandler handler = new CharacteristicsSAXHandler(type);
	        saxParser.parse(new 
	        		File(path), 
	        		handler);
	       List<ObjParam> list = handler.getObjParams();
	       set = new HashSet<ObjParam>(list);
	           
	    } catch (ParserConfigurationException | SAXException | IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	private void parseMapObjChar(){
		parse(mapObjCharPath, this.allMapObjCharParams, ObjType.MAP_CHAR);
	}
	
	private void parsePlayerChar(){
		parse(this.playerCharPath, this.allPlayerCharParams, ObjType.PLAYER_CHAR);
		
	}
	
	private void parseOutcomes(){
		parse(this.outcomePath, this.allOutcomeParams, ObjType.OUTCOME);
		
	}
	
	private void parseCondition(){
		parse(this.conditionPath, this.allConditionParams, ObjType.CONDITION);
	}
	
	
	
	
	

}
