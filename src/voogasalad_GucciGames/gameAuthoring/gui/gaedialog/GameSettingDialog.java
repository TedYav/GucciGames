package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;
import voogasalad_GucciGames.gameAuthoring.properties.GameSettingsProperty;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
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
	private ObjectProperty gameSettingsProperty = new ObjectProperty();
	private IDialogGaeController dialogGaeController;
	private ISaveObjProperty saveObjProperty;
	private DialogElements dialogElements;
	
	
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
		dialogElements = new DialogElements(prop, gameSettingsProperty, saveObjProperty, null, null);
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
		DropDownMenuField mapSize = new DropDownMenuField(dialogElements, "mapsize", "mapsize_items");
		DropDownMenuField fogOfWar = new DropDownMenuField(dialogElements, "fogofwar", "fogofwar_items");
		RadioBtnField miniMap = new RadioBtnField(dialogElements, "minimap", "minimap_items");
		RadioBtnField zoomable = new RadioBtnField(dialogElements, "zoomable", "zoomable_items");
		ScrollBarField numPlayer = new ScrollBarField(dialogElements, "numplayer", "numplayer_items");		
		content.getChildren().addAll(titleElement, nameElement, mapSize.getContent(), fogOfWar.getContent(),
				miniMap.getContent(), zoomable.getContent(), numPlayer.getContent());
		
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
