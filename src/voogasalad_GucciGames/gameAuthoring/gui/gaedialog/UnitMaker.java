package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
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
	private ObjectProperty unitProperty = new ObjectProperty();
	private ISaveGroovy saveGroovy;
	private ISaveObjProperty saveObjProperty;
	private ISaveCustomObj saveCustomObj;
	private DialogElements dialogElements;
	private Scene scene;
	private SaveField save;
	
	public UnitMaker(DialogContent customContent, IDialogGaeController controller){
		super();
		prop = loadProperties("dialogproperties/tiledialogproperties.properties");	
		saveGroovy = super.setSaveGroovyFunctions(groovyBuffer, saveGroovy);
		saveObjProperty = super.setSavePropertyFunction(unitProperty, saveObjProperty);		
		dialogElements = new DialogElements(prop, unitProperty, saveObjProperty, saveGroovy, saveCustomObj);
		groovyTabPane = new GroovyTabPane(prop, saveGroovy);
		
		
		//myContent = initializeDialog(initializeCustomContent());
		save = new SaveField(dialogElements, controller);
		MakerDialog dialog = new MakerDialog(dialogElements, customContent, groovyTabPane, save);	
		myContent = dialog.getContent();
		Scene tileDialogScene = new Scene(myContent, WIDTH, HEIGHT);
		tileDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		unitMakerDialog.setScene(tileDialogScene);	
	}
	

//
//	@Override
//	protected VBox initializeDialog() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected VBox initializeDialog(VBox customProperties) {
//		// TODO Auto-generated method stub
//		VBox content = new VBox();
//		content = super.initDefaultContentForObjMaker(prop, customProperties, groovyTabPane, 
//				"vbox-element", saveObjProperty, null/*TODO: pass saveCustomObj */, unitProperty);
//		return content;
//	}
//
//	@Override
//	protected VBox initializeCustomContent() {
//		// TODO Auto-generated method stub
//		VBox vbox = new VBox();
//		RadioBtnField prop1 = new RadioBtnField(dialogElements, "prop1", "prop1_items");
//		HBox prop1Element = prop1.getContent();
//		HBox prop2Element = createElement(prop.getProperty("prop2"),
//				makeRadioButtons(prop, "prop2", "prop2_items", saveObjProperty), "hbox-element");	
//		vbox.getChildren().addAll(prop1Element, prop2Element);
//		vbox.setId("vbox-element");
//		return vbox;
//	}
	
	public void showUnitMakerDialog(){
		super.showDialog(unitMakerDialog);
	}


	@Override
	protected VBox initializeCustomContent() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected VBox initializeDialog(VBox customProperties) {
		// TODO Auto-generated method stub
		return null;
	}

}
