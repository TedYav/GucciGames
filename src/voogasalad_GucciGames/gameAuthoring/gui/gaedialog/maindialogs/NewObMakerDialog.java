package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.model.DefaultMapObjectType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class NewObMakerDialog extends AGaeDialog{	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private Properties prop;
	private IDialogGaeController controller;
	private GridPane gridPane = new GridPane();
	
	
	
	public NewObMakerDialog( IDialogGaeController controller){
		super();
		GaeDialogHelper helper = new GaeDialogHelper();
		prop = helper.loadProperties("dialogproperties/tiledialogproperties.properties");	
		this.controller = controller;
		DialogElements dialogElements = new DialogElements(prop, controller);	
		gridPane = new NewObjMakerPane(prop);		
		this.getDialogPane().setContent(gridPane);
		final ButtonType save = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().add(save);
		
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == save) {
		    	String[] data = ((NewObjMakerPane) gridPane).getUserInputData();
		        DefaultMapObjectType mapObjType = new DefaultMapObjectType(data[0], data[1]);
		        //TODO: save map object type
		    }
		    return null;
		});
	 }	
 
	 

}
