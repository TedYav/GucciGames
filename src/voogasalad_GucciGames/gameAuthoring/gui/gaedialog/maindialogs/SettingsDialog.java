package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.ActionVBox;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsDialog extends GaeDialog  implements ISwitchSettingsPane{
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;	
	
	private Stage settingsDialog = new Stage();
	private IDialogGaeController controller;
	private Properties prop;
	private DialogElements dialogElements;
	private Scene scene;
	private ArrayList<VBox> myContents = new ArrayList<VBox>();
	
	private Pane myPane = new Pane();
	
	private int currentIndex = 0;
	
	
	
	public SettingsDialog(IDialogGaeController controller){
		super();
		prop = loadProperties("dialogproperties/actionsettings.properties");			
		this.controller = controller;
		dialogElements = new DialogElements(prop, null, controller);
		setContentsArray();
		setScene();
		
	}
	
	private void setContentsArray(){
		myContents.add(0, new ActionVBox(dialogElements, this));
	}
	
	private void setScene(){
		myPane.getChildren().add(myContents.get(currentIndex));
		myPane.setPrefSize(WIDTH, HEIGHT);
		scene = new Scene(myPane, WIDTH, HEIGHT);		
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		settingsDialog.setScene(scene);	

	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void switchSettingsPane(int currentIndex) {
		// TODO Auto-generated method stub
		if (currentIndex + 1 < myContents.size()){
			myPane.getChildren().removeAll(myPane.getChildren());
			myPane.getChildren().add(myContents.get(currentIndex + 1)); 
		}
		
	}
	
	public void show(){
		super.showDialog(settingsDialog);
	}


}
