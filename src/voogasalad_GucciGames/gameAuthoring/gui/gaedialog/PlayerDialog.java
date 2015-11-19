package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private List<PlayerContent> contentList = new ArrayList<PlayerContent>();
	private Properties prop;
	private ISaveObjProperty saveObjProperty;
	private DialogElements dialogElements;
	private Scene scene;
	private int numOfPlayers;
	private Button saveBtn = new Button("Save");
	private ScrollPane scrollPane = new ScrollPane();

	
	public PlayerDialog(IDialogGaeController controller, int numberOfPlayers) {		
		super();
		prop = loadProperties("dialogproperties/playerdialogproperties.properties");			
		this.controller = controller;
		this.numOfPlayers = numberOfPlayers;		
		initialize();
		setScene();	
		
	}
	
	private void setScene(){
		scrollPane.setContent(myContent);
		scrollPane.setPrefSize(WIDTH, HEIGHT);
		scene = new Scene(scrollPane, WIDTH, HEIGHT);		
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		playerDialog.setScene(scene);	
		
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		
		Text title = new Text(prop.getProperty("title"));
		title.setId("title");
		myContent.getChildren().add(title);
		int num = 1;
		if (numOfPlayers == 0){
			Text warning = new Text(prop.getProperty("noplayerwarning"));
			warning.setId("light");
			myContent.getChildren().add(warning);
		}
		while(numOfPlayers  >=  num) {
			PlayerContent content = new PlayerContent(num, controller);
			contentList.add(content);
			ObjectProperty playerProperty = new ObjectProperty();
			saveObjProperty = setSavePropertyFunction(playerProperty, saveObjProperty);		
			dialogElements = new DialogElements(prop, playerProperty, saveObjProperty, null,controller);
			dialogElements.getSaveObjProperty().saveObjProperty("type", "playersetting");
			content.setDialogElements(dialogElements);
			content.init();
			myContent.getChildren().add(content.getContent());
			num++;
		}
		
		initSaveBtn();
		myContent.setId("vbox-element");

	}
	
	private void initSaveBtn(){
		
		saveBtn.setOnAction(e -> {
			for(PlayerContent c: contentList){
				String name = c.getPlayerName();
				int id = c.getPlayerId();
				controller.addPlayerToList(name, id);
			}
			
		});
		this.myContent.getChildren().add(saveBtn);
	}
	
	
	public void showDialog(){
		super.showDialog(playerDialog);
	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		return null;
	}

}
