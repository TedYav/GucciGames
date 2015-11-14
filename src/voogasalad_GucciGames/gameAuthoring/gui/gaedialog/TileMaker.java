package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;
import voogasalad_GucciGames.gameAuthoring.properties.TileProperty;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TileMaker extends NewObjectMaker {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	GroovyTabPane groovyTabPane;
	private VBox myContent = new VBox();
	private Stage tileMakerDialog = new Stage();
	private Map<Integer, String> groovyBuffer = new HashMap<Integer, String>();
	private Properties prop;
	private TileProperty tileProperty = new TileProperty();
	private ISaveGroovy saveGroovy;
	private ISaveObjProperty saveObjProperty;
	
	public TileMaker(){
		prop = loadProperties("dialogproperties/tiledialogproperties.properties");	
		
		saveGroovy = (str, index) -> {
			groovyBuffer.put(index, str);
			//debug
			groovyBuffer.forEach((k, v) -> System.out.println(" " + k + " " + v));
			System.out.println("---------");
		};	
		saveObjProperty = (propName, prop) -> {
			try {
				tileProperty.addPropertyElement(propName, prop);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		groovyTabPane = new GroovyTabPane(prop, saveGroovy);
		myContent = initializeDialog(initializeCustomProperties());
		Scene tileDialogScene = new Scene(myContent, WIDTH, HEIGHT);
		tileDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		tileMakerDialog.setScene(tileDialogScene);		
	}
	
	protected VBox initializeCustomProperties(){
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
		content = super.initDefaultContentForObjMaker(prop, customProperties, groovyTabPane, 
				"vbox-element", saveObjProperty);
		return content;
	}
	
	public void showGameSettingsDialog(){
		tileMakerDialog.initModality(Modality.APPLICATION_MODAL);
		tileMakerDialog.show();
	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		return null;
	}





}
   




