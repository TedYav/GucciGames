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
		dialogElements.getDialogProperties().setProperty("type", "gamesetting");
		prop = loadProperties("dialogproperties/gamedialog.properties");
		this.initializeSaveObjProperty();
		dialogElements = new DialogElements(prop, gameSettingsProperty, saveObjProperty, null, dialogGaeController);
		SaveField saveField = new SaveField(dialogElements, dialogGaeController);
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
		TextInputField nameText = new TextInputField(dialogElements, "name");
		DropDownMenuField mapSize = new DropDownMenuField(dialogElements, "mapsize", "mapsize_items");
		DropDownMenuField fogOfWar = new DropDownMenuField(dialogElements, "fogofwar", "fogofwar_items");
		RadioBtnField miniMap = new RadioBtnField(dialogElements, "minimap", "minimap_items");
		RadioBtnField zoomable = new RadioBtnField(dialogElements, "zoomable", "zoomable_items");
		ScrollBarField numPlayer = new ScrollBarField(dialogElements, "numplayer", "numplayer_items");	
		
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
