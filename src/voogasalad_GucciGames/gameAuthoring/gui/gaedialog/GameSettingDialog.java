package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
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
	private GameSettingParams gameSettingParams = new GameSettingParams();
	
	private TextInputField nameText;
	private DropDownMenuField mapSize;
	private DropDownMenuField fogOfWar;
	private RadioBtnField miniMap ;
	private RadioBtnField zoomable;
	private ScrollBarField numPlayer;
	
	
	public GameSettingDialog(IDialogGaeController dialogGaeController){
		this.dialogGaeController = dialogGaeController;
		prop = loadProperties("dialogproperties/gamedialog.properties");
		this.initializeSaveObjProperty();
		dialogElements = new DialogElements(prop, gameSettingsProperty, saveObjProperty, null, dialogGaeController);
		dialogElements.getSaveObjProperty().saveObjProperty("type", "gamesetting");
		SaveField saveField = new SaveField(dialogElements, dialogGaeController, gameSettingDialog);
		
		saveField.getSaveBtn().setOnAction(e -> {
			gameSettingParams.setName(nameText.getTextInput());
			gameSettingParams.setMapSize(mapSize.getSelected());
			gameSettingParams.setFogOfWar(fogOfWar.getSelected().toString() == "Yes" ? true: false);
			gameSettingParams.setNumberOfPlayers(numPlayer.getSelected());
			dialogGaeController.saveGameSetting(gameSettingParams);
			
	
			});
			
		
		myContent.getChildren().addAll(this.initializeDialog(), saveField.getContent());
		Scene gameSettingDialogScene = new Scene(myContent, 500, 500);
		gameSettingDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		gameSettingDialog.setScene(gameSettingDialogScene);		
	}
	
	private void initializeSaveObjProperty(){
		saveObjProperty = (propName, prop) -> {
			try {
				gameSettingsProperty.addPropertyElement(propName, prop);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	}

	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		VBox content = new VBox();				
		Text titleElement = new Text();
		titleElement.setText(prop.getProperty("title"));
		nameText = new TextInputField(dialogElements, "name");
		mapSize = new DropDownMenuField(dialogElements, "mapsize", "mapsize_items");
		 fogOfWar = new DropDownMenuField(dialogElements, "fogofwar", "fogofwar_items");
		miniMap = new RadioBtnField(dialogElements, "minimap", "minimap_items");
		 zoomable = new RadioBtnField(dialogElements, "zoomable", "zoomable_items");
		numPlayer = new ScrollBarField(dialogElements, "numplayer", "numplayer_items");	
		
		content.getChildren().addAll(titleElement, nameText.getContent(), mapSize.getContent(), fogOfWar.getContent(),
				miniMap.getContent(), zoomable.getContent(), numPlayer.getContent());		
		content.getChildren().forEach(hbox->hbox.setId("hbox-element"));		
		titleElement.setId("title");
		content.setId("vbox-element");	
		return content;
	}

	public void showGameSettingsDialog(){
		super.showDialog(gameSettingDialog);
	}


}
