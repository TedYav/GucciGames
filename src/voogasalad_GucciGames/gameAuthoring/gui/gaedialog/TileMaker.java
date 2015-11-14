package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	
//	private TabPane tabPane = new TabPane();
//	private Tab currentTab;
	GroovyTabPane groovyTabPane;
	private VBox myContent = new VBox();
	private Stage tileMakerDialog = new Stage();
	private Map<Integer, String> groovyBuffer = new HashMap<Integer, String>();
	private Properties prop;
	
	public TileMaker(){
		prop = loadProperties("dialogproperties/tiledialogproperties.properties");	
		ISaveGroovy saveGroovy = (str, index) -> {
			groovyBuffer.put(index, str);
			//debugg
			groovyBuffer.forEach((k, v) -> System.out.println(" " + k + " " + v));
			System.out.println("---------");
		};
		groovyTabPane = new GroovyTabPane(prop, saveGroovy);
		myContent = initializeDialog();
		Scene tileDialogScene = new Scene(myContent, WIDTH, HEIGHT);
		tileDialogScene .getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		tileMakerDialog.setScene(tileDialogScene);		
	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		VBox content = new VBox();
		content = super.initializeDefaultContent(prop, groovyTabPane);
		return content;
	}
	
	public void showGameSettingsDialog(){
		tileMakerDialog.initModality(Modality.APPLICATION_MODAL);
		tileMakerDialog.show();
	}





}
   




