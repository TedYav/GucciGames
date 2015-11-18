package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerDialog extends GaeDialog  {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	
	private VBox myContent = new VBox();
	
	private Stage playerDialog = new Stage();
	
	private Properties prop;
	private ObjectProperty playerProperty = new ObjectProperty();
	private ISaveObjProperty saveObjProperty;
	private ISaveCustomObj saveCustomObj;
	private DialogElements dialogElements;
	private Scene scene;
	private SaveField save;
	private int numOfPlayers;

	
	public PlayerDialog(IDialogGaeController controller, int numberOfPlayers) {
		
		super();
		prop = loadProperties("dialogproperties/playerdialogproperties.properties");			
		saveObjProperty = setSavePropertyFunction(playerProperty, saveObjProperty);		
		dialogElements = new DialogElements(prop, playerProperty, saveObjProperty, null, saveCustomObj);
		this.numOfPlayers = numberOfPlayers;
		save = new SaveField(dialogElements, controller);
		myContent = initializeDialog();
		scene= new Scene(myContent, WIDTH, HEIGHT);		
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		playerDialog.setScene(scene);		
		
	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		VBox vbox = new VBox();
		int num = 1;
		while(numOfPlayers  >=  num) {
			TextInputField textInputField = new TextInputField(dialogElements, "player"+num);
			vbox.getChildren().add(textInputField.getContent());
			num++;
		}
		
		return vbox;
	}
	
	public void showDialog(){
		super.showDialog(playerDialog);
	}

}
