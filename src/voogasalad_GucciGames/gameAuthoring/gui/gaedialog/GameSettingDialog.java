package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;
import voogasalad_GucciGames.gameAuthoring.properties.GameSettingsProperty;
import voogasalad_GucciGames.gameAuthoring.properties.MapObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameSettingDialog extends GaeDialog {
	
	private Stage gameSettingDialog = new Stage();
	private VBox myContent = new VBox();
	private Properties prop;
	private MapObjectProperty gameSettingsProperty = new MapObjectProperty();
	private IDialogGaeController dialogGaeController;
	private ISaveObjProperty saveObjProperty;
	
	
	public GameSettingDialog(IDialogGaeController dialogGaeController){
		this.dialogGaeController = dialogGaeController;
		prop = loadProperties("dialogproperties/gamedialog.properties");	
		saveObjProperty = (propName, prop) -> {
			try {
				gameSettingsProperty.addPropertyElement(propName, prop);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		myContent = initializeDialog();
		myContent.getChildren().add(initializeControl(prop, "hbox-control", 
				dialogGaeController, gameSettingsProperty));
		Scene gameSettingDialogScene = new Scene(myContent, 500, 500);
		gameSettingDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		gameSettingDialog.setScene(gameSettingDialogScene);		
	}


	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		VBox content = new VBox();				
		Text titleElement = new Text();
		titleElement.setText(prop.getProperty("title"));
		TextField nameTextField = new TextField();	
		nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			saveObjProperty.saveObjProperty(prop.getProperty("title"), newValue);		
		});
		HBox nameElement = createElement(prop.getProperty("name"), nameTextField, "field-title-element");
		HBox mapSizeElement = createElement(prop.getProperty("mapsize"), 
				makeDropDownList(prop,"mapsize", "mapsize_items", saveObjProperty), "field-title-element");
		HBox fogOfWarElement = createElement(prop.getProperty("fogofwar"),
				makeDropDownList(prop, "fogofwar", "fogofwar_items", saveObjProperty), "field-title-element");
		HBox miniMapElement = createElement(prop.getProperty("minimap"),
				makeRadioButtons(prop, "minimap", "minimap_items",saveObjProperty), "field-title-element");
		HBox zoomableElement = createElement(prop.getProperty("zoomable"),
				makeRadioButtons(prop, "zoomable","zoomable_items" , saveObjProperty), "field-title-element");
		HBox numPlayerElement = createElement(prop.getProperty("numplayer"),
				makeScrollBar(prop, "numplayer","player_min", "player_max", "player_increment", saveObjProperty), "field-title-element");
		content.getChildren().addAll(titleElement, nameElement, mapSizeElement, fogOfWarElement,
				miniMapElement, zoomableElement, numPlayerElement);
		content.getChildren().forEach(hbox->hbox.setId("hbox-element"));
		titleElement.setId("title");
		content.setId("vbox-element");	
		return content;
	}

	@Override
	protected VBox initializeDialog(VBox customProperties) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void showGameSettingsDialog(){
		super.showDialog(gameSettingDialog);
	}


}
