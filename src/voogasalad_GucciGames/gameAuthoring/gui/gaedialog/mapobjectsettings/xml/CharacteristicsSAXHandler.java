package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.GaeDialogHelper;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;

public class CharacteristicsSAXHandler extends DefaultHandler {

	private List<ObjectParam> characteristicsParams = new ArrayList<ObjectParam>();
	private ObjectParam characteristicsParam = null;
	private final GaeDialogHelper helper = new GaeDialogHelper();
	private ObjType type;

	public CharacteristicsSAXHandler(ObjType type) {
		this.type = type;
	}

	public List<ObjectParam> getObjParams() {
		return characteristicsParams;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if ("element".equals(qName)) {
			String name = attributes.getValue("name");

			characteristicsParam = new ObjectParam(name, type, -1);
			List<String> paramNames = helper.parseStringToList(attributes.getValue("paramNames"));
			List<String> paramTypes = helper.parseStringToList(attributes.getValue("paramTypes"));
			for (int i = 0; i < paramNames.size(); i++) {
				characteristicsParam.addParam(paramTypes.get(i), paramNames.get(i));
				characteristicsParam.addParamOrder(i, paramNames.get(i));
			}

		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("element") && characteristicsParam != null) {
			this.characteristicsParams.add(characteristicsParam);
		}

	}

	public void characters(char ch[], int start, int length) throws SAXException {

	}

}
