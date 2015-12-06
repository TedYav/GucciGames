package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveObjProperty;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerDialog extends AGaeDialog {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;	
	private VBox myContent = new VBox();	
	private IDialogGaeController controller;
	private List<PlayerContent> contentList = new ArrayList<PlayerContent>();
	private Properties prop;
	private DialogElements dialogElements;
	private int numOfPlayers;
	private Button saveBtn = new Button("Save");
	private ScrollPane scrollPane = new ScrollPane();


	public PlayerDialog(IDialogGaeController controller, int numberOfPlayers) {		
		super();
		GaeDialogHelper helper = new GaeDialogHelper();
		prop = helper.loadProperties("dialogproperties/playerdialogproperties.properties");			
		this.controller = controller;
		this.numOfPlayers = numberOfPlayers;
		
		final ButtonType save = new ButtonType("Save", ButtonData.FINISH);		
		initialize();
		setScene();	

	}

	private void setScene(){
		scrollPane.setContent(myContent);
		scrollPane.setPrefSize(WIDTH, HEIGHT);
		this.getDialogPane().setContent(scrollPane);

	}

	protected void initialize() {

		Text title = new Text(prop.getProperty("title"));
		title.setId("title");
		myContent.getChildren().add(title);
		int num = 1;
		if (numOfPlayers == 0){
			Text warning = new Text(prop.getProperty("noplayerwarning"));
			warning.setId("light");
			myContent.getChildren().add(warning);
		} else {


			while(numOfPlayers  >=  num) {
				PlayerContent content = new PlayerContent(num, controller, prop);
				contentList.add(content);
				dialogElements = new DialogElements(prop, controller);
				content.setDialogElements(dialogElements);
				content.init();
				myContent.getChildren().add(content);
				num++;
			}
			this.myContent.getChildren().add(saveBtn);
		}

		initSaveBtn();
		myContent.setId("vbox-element");

	}

	private void initSaveBtn(){

		saveBtn.setOnAction(e -> {
			for(PlayerContent c: contentList){
				String name = c.getPlayerName();
				int id = c.getPlayerId();
				int numMoves = c.getNumMoves();
				controller.addPlayerToList(name, id);
				// Save Player
				PlayerParams params = new PlayerParams(id, name, numMoves);
				controller.savePlayer(params);
				this.close();
			}

		});

	}


}
