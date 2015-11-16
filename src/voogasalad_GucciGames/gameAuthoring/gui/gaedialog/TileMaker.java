package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;
import voogasalad_GucciGames.gameAuthoring.properties.MapObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.TileProperty;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TileMaker extends NewObjectMaker {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	static GroovyTabPane groovyTabPane;
	private VBox myContent = new VBox();
	private Stage tileMakerDialog = new Stage();
	private Map<Integer, String> groovyBuffer = new HashMap<Integer, String>();
	private Properties prop;
	private MapObjectProperty tileProperty = new MapObjectProperty();
	private ISaveGroovy saveGroovy;
	private ISaveObjProperty saveObjProperty;
	private ISaveCustomObj saveCustomObject;

	
	public TileMaker(ISaveCustomObj saveCustomObj){
		super();
		//this.saveCustomObj = saveCustomObj;
		prop = loadProperties("dialogproperties/tiledialogproperties.properties");	
		saveGroovy = super.setSaveGroovyFunctions(groovyBuffer, saveGroovy);
		saveObjProperty = super.setSavePropertyFunction(tileProperty, saveObjProperty);
		//
		this.saveCustomObject = super.setSaveCustomObj(saveCustomObject);
		groovyTabPane = new GroovyTabPane(prop, saveGroovy);
		myContent = initializeDialog(initializeCustomContent());
		Scene tileDialogScene = new Scene(myContent, WIDTH, HEIGHT);
		tileDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		tileMakerDialog.setScene(tileDialogScene);		
	}
	
	
	protected VBox initializeCustomContent(){
		VBox vbox = new VBox();
		HBox prop1Element = createElement(prop.getProperty("prop1"),
				makeRadioButtons(prop, "prop1", "prop1_items", saveObjProperty), "hbox-element");
		HBox prop2Element = createElement(prop.getProperty("prop2"),
				makeRadioButtons(prop, "prop2", "prop2_items", saveObjProperty), "hbox-element");	
		vbox.getChildren().addAll(prop1Element, prop2Element);
		vbox.setId("vbox-element");
		return vbox;
	}
	

	@Override
	protected VBox initializeDialog(VBox customProperties) {
		// TODO Auto-generated method stub
		VBox content = new VBox();
		if(saveCustomObject == null){
			System.out.println("null");
		}
		content = initDefaultContentForObjMaker(prop, customProperties, groovyTabPane, 
				"vbox-element", saveObjProperty, saveCustomObject, tileProperty);
		return content;
	}
	
	public void showTileMakerDialog(){
		super.showDialog(tileMakerDialog);
	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		return null;
	}





}
   




