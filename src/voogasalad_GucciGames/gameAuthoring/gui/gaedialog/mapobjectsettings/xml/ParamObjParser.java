package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.control.Label;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ObjParamPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;

public class ParamObjParser {
	
	private Set<ObjParam> allMapObjCharParams = new HashSet<ObjParam>();
	
	private Set<ObjParam> allPlayerCharParams = new HashSet<ObjParam>();
	
	private Set<ObjParam> allOutcomeParams = new HashSet<ObjParam>();
	
	private Set<ObjParam> allConditionParams = new HashSet<ObjParam>();
	
	private static final String mainPath = "src/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/mapobjectsettings/xml/";
	private static final String mapObjCharPath = mainPath+"mapObjCharDependencies.xml";
	private static final String playerCharPath = mainPath+"playerCharDependencies.xml";
	private static final String outcomePath = mainPath+"outcomeDependencies.xml";
	private static final String conditionPath = mainPath+"conditionDependencies.xml";
	
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
	
	private void parseAll(){
		parseOutcomes();
		parseMapObjChar();
		parsePlayerChar();
		parseCondition();
		
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
	       list.forEach(e -> {
	    	   System.out.println("added: " + e.getName());
	       });
	       set = new HashSet<ObjParam>(list);
	       System.out.println("size " + set.size());
	           
	    } catch (ParserConfigurationException | SAXException | IOException ex) {
	        ex.printStackTrace();
	    }
	    System.out.println("done");
	}
	
	private void parseMapObjChar(){
		parse(mapObjCharPath, this.allMapObjCharParams, ObjType.MAP_CHAR);
		System.out.println("size 2: " + this.allMapObjCharParams.size());
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
