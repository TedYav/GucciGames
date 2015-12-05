package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.GaeDialogHelper;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;

public class CharacteristicsSAXHandler extends DefaultHandler {
	
	private Set<String> selectedCharacteristics = new HashSet<String>();
	private List<GCharParam> characteristicsParams = new ArrayList<GCharParam>();
	private GCharParam characteristicsParam = null;
	private final GaeDialogHelper helper = new GaeDialogHelper();
	
	public CharacteristicsSAXHandler(Set<String> selectedCharacteristics ){
		this.selectedCharacteristics = selectedCharacteristics;
	}
	
	private boolean bDisplayName = false;
	
	public List<GCharParam> getCharParams(){
		return characteristicsParams ;
	}
	  public void startElement(String uri, String localName,
		        String qName, Attributes attributes) throws SAXException {
		        if("characteristic".equals(qName)){
		            String name = attributes.getValue("name");
		            if (selectedCharacteristics.contains(name)){
		            	characteristicsParam = new GCharParam(name);
		            	characteristicsParam.setDisplayName(attributes.getValue("displayName"));
		            	List<String> paramNames = helper.parseStringToList(
		            			attributes.getValue("paramNames"));
		            	List<String> paramTypes = helper.parseStringToList(
		            			attributes.getValue("paramTypes"));
		            	for(int i = 0; i < paramNames.size(); i++){
		            		characteristicsParam.addParam(paramTypes.get(i), paramNames.get(i));
		            	}
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
