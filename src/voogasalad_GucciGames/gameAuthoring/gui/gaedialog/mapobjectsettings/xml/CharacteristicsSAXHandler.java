package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.CharacteristicsParam;

public class CharacteristicsSAXHandler extends DefaultHandler {
	
	private Set<String> selectedCharacteristics = new HashSet<String>();
	private List<CharacteristicsParam> characteristicsParams = new ArrayList<CharacteristicsParam>();
	private CharacteristicsParam characteristicsParam = null;
	
	public CharacteristicsSAXHandler(Set<String> selectedCharacteristics ){
		this.selectedCharacteristics = selectedCharacteristics;
	}
	
	private boolean bDisplayName = false;
	
	public List<CharacteristicsParam> getCharParams(){
		return characteristicsParams ;
	}
	  public void startElement(String uri, String localName,
		        String qName, Attributes attributes) throws SAXException {
		        if("characteristic".equals(qName)){
		            String name = attributes.getValue("name");
		            if (selectedCharacteristics.contains(name)){
		            	characteristicsParam = new CharacteristicsParam(name);
		            	characteristicsParam.setDisplayName(attributes.getValue("displayName"));
			            characteristicsParam.setMin(attributes.getValue("min"));
			            characteristicsParam.setMax(attributes.getValue("max"));
		            } 
		            
		        }           

		    }
	   public void endElement(String uri, String localName,
		        String qName) throws SAXException {
		   if(qName.equalsIgnoreCase("characteristic") && characteristicsParam != null){
			   this.characteristicsParams.add(characteristicsParam);
		   }

		        
	   }
	   
	   public void characters(char ch[], int start, int length) throws SAXException {
		 
	   }


}
