package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;

public class RulesSAXHandler extends DefaultHandler {
	private List<RuleParams> rules = new ArrayList<RuleParams>();
	private RuleParams ruleParam;

	public RulesSAXHandler() {
	}

	public List<RuleParams> getRuleParams() {
		return rules;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("rule".equals(qName)) {
			String name = attributes.getValue("name");

			ruleParam = new RuleParams(name);

		}

	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("rule") && ruleParam != null) {
			this.rules.add(ruleParam);
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException {

	}
}
