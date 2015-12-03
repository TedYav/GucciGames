package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ActionVBox;
import javafx.scene.Node;
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
	private ActionVBox actionVBox;
	
	
	
	public SettingsDialog(IDialogGaeController controller){
		super();
		prop = loadProperties("dialogproperties/actionsettings.properties");			
		this.controller = controller;
		dialogElements = new DialogElements(prop, null, controller);
		actionVBox = new ActionVBox(dialogElements, this);
		actionVBox.setPrefSize(WIDTH, HEIGHT);
		myContents.add(actionVBox);
		setScene();
		
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
	public void switchSettingsPane(Node n) {
		// TODO Auto-generated method stub
			myPane.getChildren().removeAll(myPane.getChildren());
			myPane.getChildren().add(n); 
		
		
	}
	
	public void show(){
		super.showDialog(settingsDialog);
	}


}
