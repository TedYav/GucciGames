package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.xml.sax.helpers.DefaultHandler;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;

public class ActionSAXHandler extends DefaultHandler {

	private Set<String> selectedActions = new HashSet<String>();
	private List<ActionParam> actionParams = new ArrayList<ActionParam>();
	private ActionParam actionParam = null;

	public ActionSAXHandler(){
		
	}

	public List<ActionParam> getActionParams(){
		return actionParams;
	}




	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException {
		if("action".equals(qName)){
			String displayName = attributes.getValue("displayName");
			String name = attributes.getValue("name");
			String rules = attributes.getValue("rules");
			String chars = attributes.getValue("chars");
			if (selectedActions.contains(displayName)){
				actionParam = new ActionParam(name);
				actionParam.setCharacteristics(chars);
				actionParam.setRules(rules);
			} 
		}


	}
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if(qName.equalsIgnoreCase("action") && actionParam != null){
			this.actionParams.add(actionParam);
		}


	}

	public void characters(char ch[], int start, int length) throws SAXException {

	}



}
