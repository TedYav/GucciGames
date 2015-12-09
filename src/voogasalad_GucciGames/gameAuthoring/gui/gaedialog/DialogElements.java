package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
/**
 * Data class that stores information for setting up and saving states from dialog
 * 
 * @author yingqi
 *
 */
public class DialogElements {
	
	private Properties prop;
	private IDialogGaeController dialogGaeController;
	
	public DialogElements(Properties prop, IDialogGaeController dialogGaeController ){
		this.prop = prop;
		this.dialogGaeController = dialogGaeController;
	}
	
	public Properties getDialogProperties(){
		return prop;
	}
	
	
	public IDialogGaeController getDialogGaeController(){
		return dialogGaeController;
	}
	
	
	

}
