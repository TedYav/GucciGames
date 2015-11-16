package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.properties.MapObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.TileProperty;
import voogasalad_GucciGames.gameAuthoring.properties.UnitProperty;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UnitMaker extends NewObjectMaker {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private GroovyTabPane groovyTabPane;
	private VBox myContent = new VBox();
	private Stage unitMakerDialog = new Stage();
	private Map<Integer, String> groovyBuffer = new HashMap<Integer, String>();
	private Properties prop;
	private MapObjectProperty unitProperty = new MapObjectProperty();
	private ISaveGroovy saveGroovy;
	private ISaveObjProperty saveObjProperty;
	private Scene scene;
	
	public UnitMaker(){
		super();
		prop = loadProperties("dialogproperties/tiledialogproperties.properties");	
		saveGroovy = super.setSaveGroovyFunctions(groovyBuffer, saveGroovy);
		saveObjProperty = super.setSavePropertyFunction(unitProperty, saveObjProperty);		
		groovyTabPane = new GroovyTabPane(prop, saveGroovy);
		myContent = initializeDialog(initializeCustomContent());
		Scene tileDialogScene = new Scene(myContent, WIDTH, HEIGHT);
		tileDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		unitMakerDialog.setScene(tileDialogScene);	
	}
	


	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected VBox initializeDialog(VBox customProperties) {
		// TODO Auto-generated method stub
		VBox content = new VBox();
		content = super.initDefaultContentForObjMaker(prop, customProperties, groovyTabPane, 
				"vbox-element", saveObjProperty, null/*TODO: pass saveCustomObj */, unitProperty);
		return content;
	}

	@Override
	protected VBox initializeCustomContent() {
		// TODO Auto-generated method stub
		VBox vbox = new VBox();
		HBox prop1Element = createElement(prop.getProperty("prop1"),
				makeRadioButtons(prop, "prop1", "prop1_items", saveObjProperty), "hbox-element");
		HBox prop2Element = createElement(prop.getProperty("prop2"),
				makeRadioButtons(prop, "prop2", "prop2_items", saveObjProperty), "hbox-element");	
		vbox.getChildren().addAll(prop1Element, prop2Element);
		vbox.setId("vbox-element");
		return vbox;
	}
	
	public void showUnitMakerDialog(){
		super.showDialog(unitMakerDialog);
	}

}
