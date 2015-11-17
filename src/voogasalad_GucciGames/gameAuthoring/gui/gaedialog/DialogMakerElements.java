package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.Property;
/**
 * Data class that stores information for setting up and saving states from dialog
 * 
 * @author yingqi
 *
 */
public class DialogMakerElements {
	
	private Properties prop;
	private ObjectProperty objProp;	
	private ISaveObjProperty saveObjProp;
	private ISaveGroovy saveGroovy;
	private ISaveCustomObj saveCustomObj;
	
	public DialogMakerElements(Properties prop, ObjectProperty objProp, 
			ISaveObjProperty saveObjProp, ISaveGroovy saveGroovy, ISaveCustomObj saveCustomObj){
		this.prop = prop;
		this.objProp = objProp;		
		this.saveObjProp = saveObjProp;	
		this.saveGroovy = saveGroovy;
		this.saveCustomObj = saveCustomObj;		
	}
	
	protected Properties getDialogProperties(){
		return prop;
	}
	
	protected ObjectProperty getObjectProperty(){
		return objProp;
	}
	
	protected ISaveObjProperty getSaveObjProperty(){
		return saveObjProp;
	}
	
	protected ISaveGroovy getSaveGroovy(){
		return saveGroovy;
	}
	
	protected ISaveCustomObj getSaveCustomObj(){
		return saveCustomObj;
	}
	
	
	

}
