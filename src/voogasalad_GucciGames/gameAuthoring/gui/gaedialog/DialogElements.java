package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.Property;
/**
 * Data class that stores information for setting up and saving states from dialog
 * 
 * @author yingqi
 *
 */
public class DialogElements {
	
	private Properties prop;
	private ObjectProperty objProp;	
	private ISaveObjProperty saveObjProp;
	private ISaveGroovy saveGroovy;
	private IDialogGaeController dialogGaeController;
	
	public DialogElements(Properties prop, ObjectProperty objProp, 
			ISaveObjProperty saveObjProp, ISaveGroovy saveGroovy,IDialogGaeController dialogGaeController ){
		this.prop = prop;
		this.objProp = objProp;		
		this.saveObjProp = saveObjProp;	
		this.saveGroovy = saveGroovy;
		this.dialogGaeController = dialogGaeController;
	}
	
	public Properties getDialogProperties(){
		return prop;
	}
	
	public ObjectProperty getObjectProperty(){
		return objProp;
	}
	
	public ISaveObjProperty getSaveObjProperty(){
		return saveObjProp;
	}
	
	public ISaveGroovy getSaveGroovy(){
		return saveGroovy;
	}
	
	public IDialogGaeController getDialogGaeController(){
		return dialogGaeController;
	}
	
	
	

}
