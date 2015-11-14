package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TileMaker extends NewObjectMaker {
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	
	
	private TabPane tabPane = new TabPane();
	private VBox myContent = new VBox();
	private Stage tileMakerDialog = new Stage();
	private Properties prop;
	
	public TileMaker(){
		prop = loadProperties("dialogproperties/tiledialogproperties.properties");	
		myContent = initializeDialog();
		Scene tileDialogScene = new Scene(myContent, WIDTH, HEIGHT);
		tileDialogScene .getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		tileMakerDialog.setScene(tileDialogScene );
		
	}
	



	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		VBox content = new VBox();
		Text titleTextElement = new Text(prop.getProperty("title"));
		TextField nameTextField = new TextField();		
		HBox nameElement = createElement(prop.getProperty("name"), 
				nameTextField, "field-title-element");
		HBox imageElement = createElement(prop.getProperty("image"),
				super.makeBrowseElement(prop, "browse", "filechoosertitle"), "" );
		makeGroovyTab(prop, "groovytitle", tabPane);
		content.getChildren().addAll(titleTextElement, nameElement, imageElement, tabPane);
		return content;
	}
	
	public void showGameSettingsDialog(){
		tileMakerDialog.initModality(Modality.APPLICATION_MODAL);
		tileMakerDialog.show();
	}

	
	

}
   




