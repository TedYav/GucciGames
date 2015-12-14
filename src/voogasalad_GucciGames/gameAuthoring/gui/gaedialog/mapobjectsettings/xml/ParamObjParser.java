package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;

public class ParamObjParser {

	private Set<ObjectParam> allMapObjCharParams = new HashSet<ObjectParam>();

	private Set<ObjectParam> allPlayerCharParams = new HashSet<ObjectParam>();

	private Set<ObjectParam> allOutcomeParams = new HashSet<ObjectParam>();

	private Set<ObjectParam> allConditionParams = new HashSet<ObjectParam>();

	Set<RuleParams> allRules = new HashSet<RuleParams>();

	Set<ActionParam> allActions = new HashSet<ActionParam>();

	private static final String mainPath = "src/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/mapobjectsettings/xml/";
	private static final String mapObjCharPath = mainPath + "mapObjCharDependencies.xml";
	private static final String playerCharPath = mainPath + "playerCharDependencies.xml";
	private static final String outcomePath = mainPath + "outcomeDependencies.xml";
	private static final String conditionPath = mainPath + "conditionDependencies.xml";
	private static final String actionPath = mainPath + "actionDependencies.xml";
	private static final String rulePath = mainPath + "ruleDependencies.xml";

	public ParamObjParser() {
		parseAll();
	}

	public Set<ObjectParam> getMapObjChars() {
		return allMapObjCharParams;
	}

	public Set<ObjectParam> getPlayerChars() {
		return allPlayerCharParams;
	}

	public Set<ObjectParam> getOutcomes() {
		return allOutcomeParams;
	}

	public Set<ObjectParam> getConditions() {
		return allConditionParams;
	}

	public Set<RuleParams> getRules() {
		return allRules;
	}

	public Set<ActionParam> getActions() {
		return allActions;
	}

	private void parseAll() {
		parseOutcomes();
		parseMapObjChar();
		parsePlayerChar();
		parseCondition();
		parseRule();
		parseAction();

	}

	private void parseAction() {

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();

			ActionSAXHandler handler = new ActionSAXHandler();
			saxParser.parse(new File(actionPath), handler);
			List<ActionParam> list = handler.getActionParams();
			allActions.addAll(list);

		} catch (ParserConfigurationException | SAXException | IOException ex) {
			ex.printStackTrace();
		}
		return;

	}

	private void parseRule() {

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();

			RulesSAXHandler handler = new RulesSAXHandler();
			saxParser.parse(new File(rulePath), handler);
			List<RuleParams> list = handler.getRuleParams();
			allRules.addAll(list);

		} catch (ParserConfigurationException | SAXException | IOException ex) {
			ex.printStackTrace();
		}
		return;

	}

	private void parse(String path, Set<ObjectParam> set, ObjType type) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();

			CharacteristicsSAXHandler handler = new CharacteristicsSAXHandler(type);
			saxParser.parse(new File(path), handler);
			List<ObjectParam> list = handler.getObjParams();
			set.addAll(list);

		} catch (ParserConfigurationException | SAXException | IOException ex) {
			ex.printStackTrace();
		}

	}

	private void parseMapObjChar() {
		parse(mapObjCharPath, allMapObjCharParams, ObjType.MAP_CHAR);
	}

	private void parsePlayerChar() {
		parse(this.playerCharPath, allPlayerCharParams, ObjType.PLAYER_CHAR);

	}

	private void parseOutcomes() {
		parse(this.outcomePath, allOutcomeParams, ObjType.OUTCOME);

	}

	private void parseCondition() {
		parse(this.conditionPath, allConditionParams, ObjType.CONDITION);
	}

}
