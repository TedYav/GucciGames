package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.Properties;
import java.util.HashMap;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
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
	private String myType;
	
	public NewObMakerDialog( IDialogGaeController controller, String type){
		super();
		myType = type;
		GaeDialogHelper helper = new GaeDialogHelper();
		prop = helper.loadProperties("dialogproperties/tiledialogproperties.properties");	
		this.controller = controller;
		DialogElements dialogElements = new DialogElements(prop, controller);	
		gridPane = new NewObjMakerPane(prop);		
		this.getDialogPane().setContent(gridPane);
		this.getDialogPane().getButtonTypes().add(mySave);
	 }


	@Override
	protected void setSaveAction() {		
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == mySave) {
		    	String[] data = ((NewObjMakerPane) gridPane).getUserInputData();
		        MapObjectType mapObjType = new MapObjectType(data[0], data[1]);
		        HashMap<String, String> currMap = new HashMap<String, String>();
		        currMap.put(data[0], data[1]);
		        //TODO: save map object type
		        switch (myType) {
		        case "unit":
		        	controller.createCustomUnitType(currMap);
		        case "tile":
		        	controller.createCustomTileType(currMap);
		        case "structure":
		        	controller.createCustomStructureType(currMap);
		        }
		    }
		    return null;
		});
	 }	
 
	 

}
