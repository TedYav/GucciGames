package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;

public class RulesSAXHandler extends DefaultHandler{
	private Set<String> selectedRules = new HashSet<String>();
	private List<RuleParams> rules = new ArrayList<RuleParams>();
	private RuleParams ruleParam;

	public RulesSAXHandler(Set<String> selectedRules){
		this.selectedRules = selectedRules;
	} 

	public List<RuleParams> getRuleParams(){
		return rules;
	}

	

	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException {
		if("rule".equals(qName)){
			String name = attributes.getValue("name");
			if (selectedRules.contains(name)){
				ruleParam = new RuleParams(name);
				ruleParam.setDisplayName(attributes.getValue("displayName"));				
			}
			
		}       

	}
	
	
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if(qName.equalsIgnoreCase("rule") && ruleParam != null){
			this.rules.add(ruleParam);
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException {
	
	}
}
