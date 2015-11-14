package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
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
	private HBox control = new HBox();

	private Properties prop;
	
	public GameSettingDialog(){
		prop = loadProperties("dialogproperties/gamedialog.properties");	
		myContent = initializeDialog();
		myContent.getChildren().add(initializeControl(prop, "hbox-control"));
		Scene gameSettingDialogScene = new Scene(myContent, 500, 500);
		gameSettingDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		gameSettingDialog.setScene(gameSettingDialogScene);
		
		
	}

	public void showGameSettingsDialog(){
		gameSettingDialog.initModality(Modality.APPLICATION_MODAL);
		gameSettingDialog.show();
	}
	


	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		VBox content = new VBox();	
		content.setId("vbox-content");
		
		Text titleElement = new Text();
		titleElement.setText(prop.getProperty("title"));

		TextField nameTextField = new TextField();		
		HBox nameElement = createElement(prop.getProperty("name"), nameTextField, "field-title-element");
		HBox mapSizeElement = createElement(prop.getProperty("mapsize"), 
				makeDropDownList(prop, "mapsize_items"), "field-title-element");
		HBox fogOfWarElement = createElement(prop.getProperty("fogofwar"),
				makeDropDownList(prop, "fogofwar_items"), "field-title-element");
		HBox miniMapElement = createElement(prop.getProperty("minimap"),
				makeRadioButtons(prop, "minimap_items"), "field-title-element");
		HBox zoomableElement = createElement(prop.getProperty("zoomable"),
				makeRadioButtons(prop, "zoomable_items"), "field-title-element");
		HBox numPlayerElement = createElement(prop.getProperty("numplayer"),
				makeScrollBar(prop, "player_min", "player_max", "player_increment"), "field-title-element");
		
		content.getChildren().addAll(titleElement, nameElement, mapSizeElement, fogOfWarElement,
				miniMapElement, zoomableElement, numPlayerElement);
		content.getChildren().forEach(hbox->hbox.setId("hbox-element"));
		titleElement.setId("title");
		
		return content;
	}
	
	
	
	public Stage getStage(){
		return gameSettingDialog;
	}


}
