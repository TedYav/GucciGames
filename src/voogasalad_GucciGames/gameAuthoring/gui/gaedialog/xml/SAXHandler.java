package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.xml.sax.helpers.DefaultHandler;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.CharacteristicsParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;

public class SAXHandler extends DefaultHandler {
	
	private Set<String> selectedActions = new HashSet<String>();
	private List<ActionParams> actionParams = new ArrayList<ActionParams>();
	private ActionParams actionParam = null;
	
	public SAXHandler(Set<String> selectedActions){
		this.selectedActions = selectedActions;
	}
	
	public List<ActionParams> getActionParams(){
		return actionParams;
	}
	
	private boolean bDisplayName = false;
	private boolean bName = false;
	private boolean bRules = false;
	private boolean bCharacteristics = false;
	
	
	   public void startElement(String uri, String localName,
		        String qName, Attributes attributes) throws SAXException {
		        if("action".equals(qName)){
		            String displayName = attributes.getValue("displayName");
		            if (!selectedActions.contains(displayName)){
		            	System.out.println("does not contain: " + displayName);
		            	return;
		            }
		            actionParam = new ActionParams();
		        } else if(qName.equalsIgnoreCase("displayName")){
		        	this.bDisplayName = true;
		        } else if (qName.equalsIgnoreCase("rules")){
		        	this.bRules = true;
		        } else if (qName.equalsIgnoreCase("characteristics")){
		        	this.bCharacteristics = true;
		        }            

		    }
	   public void endElement(String uri, String localName,
		        String qName) throws SAXException {
		   if(qName.equalsIgnoreCase("action") && actionParam !=null){
			   this.actionParams.add(actionParam);
		   }

		        
	   }
	   
	    public void characters(char ch[], int start, int length) throws SAXException {
	    	if(actionParam != null){
	    	if(bName){
	    		actionParam.setName(new String(ch, start, length));
	    		bName = false;
	    	}
	        if (bDisplayName) {
	        	actionParam.setDisplayName(new String(ch, start, length));
	            bDisplayName = false;
	        } else if (bRules) {
	            actionParam.setRules(new String(ch, start, length));
	            bRules = false;
	        } else if (bCharacteristics) {
	            actionParam.setCharacteristics(new String(ch, start, length));
	            bCharacteristics = false;
	        }
	    	}
	    }
	   
	   

}
