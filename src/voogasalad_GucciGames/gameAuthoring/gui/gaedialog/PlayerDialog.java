package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerDialog extends GaeDialog  {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;	
	private VBox myContent = new VBox();	
	private Stage playerDialog = new Stage();
	private IDialogGaeController controller;
	
	private Properties prop;
	private Map<Integer, ObjectProperty> playerProperties = new HashMap<Integer, ObjectProperty>();
	private ISaveObjProperty saveObjProperty;
	private DialogElements dialogElements;
	private Scene scene;
	private int numOfPlayers;
	private ScrollPane scrollPane = new ScrollPane();

	
	public PlayerDialog(IDialogGaeController controller, int numberOfPlayers) {		
		super();
		prop = loadProperties("dialogproperties/playerdialogproperties.properties");			
		this.controller = controller;
		this.numOfPlayers = numberOfPlayers;		
		myContent = initializeDialog();
		setScene();	
		
	}
	
	private void setScene(){
		scrollPane.setContent(myContent);
		scrollPane.setPrefSize(WIDTH, HEIGHT);
		scene = new Scene(scrollPane, WIDTH, HEIGHT);		
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		playerDialog.setScene(scene);	
		
	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		VBox vbox = new VBox();
		Text title = new Text(prop.getProperty("title"));
		title.setId("title");
		vbox.getChildren().add(title);
		int num = 1;
		if (numOfPlayers == 0){
			Text warning = new Text(prop.getProperty("noplayerwarning"));
			warning.setId("light");
			vbox.getChildren().add(warning);
		}
		while(numOfPlayers  >=  num) {
			PlayerContent content = new PlayerContent(num);
			ObjectProperty playerProperty = new ObjectProperty();
			saveObjProperty = setSavePropertyFunction(playerProperty, saveObjProperty);		
			dialogElements = new DialogElements(prop, playerProperty, saveObjProperty, null,controller);
			content.setDialogElements(dialogElements);
			content.init();
			vbox.getChildren().add(content.getContent());
			SaveField save = new SaveField(dialogElements, controller);
			vbox.getChildren().add(save.getContent());
			num++;
		}
		vbox.setId("vbox-element");
		return vbox;
	}
	
	public void showDialog(){
		super.showDialog(playerDialog);
	}

}
