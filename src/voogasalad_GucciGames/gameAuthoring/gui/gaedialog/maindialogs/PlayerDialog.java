package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.lang.reflect.Constructor;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveObjProperty;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.ISwitchGroovyPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerDialog extends AGaeDialog<PlayerCharDialog> {
	private static final String gaeDialogPath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.";
	private static final String settingsPackagePath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.";

	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private VBox myContent = new VBox();
	private IDialogGaeController controller;
	private ISwitchSettingsPane settingsPaneController;
	private List<PlayerContent> playerContentList = new ArrayList<PlayerContent>();
	private Properties prop;
	private DialogElements dialogElements;
	private int numOfPlayers;
	private ScrollPane scrollPane = new ScrollPane();

	private PlayerContent playerContent;

	public PlayerDialog(IDialogGaeController controller, int numberOfPlayers) {
		super();
		GaeDialogHelper helper = new GaeDialogHelper();
		prop = helper
				.loadProperties("dialogproperties/playerdialogproperties.properties");
		this.controller = controller;
		this.numOfPlayers = numberOfPlayers;

		initialize();
		setScene();
		setSaveAction();

	}

	private void setScene() {
		scrollPane.setContent(myContent);
		scrollPane.setPrefSize(WIDTH, HEIGHT);
		this.getDialogPane().setContent(scrollPane);

	}

	protected void initialize() {

		Text title = new Text(prop.getProperty("title"));
		title.setId("title");
		myContent.getChildren().add(title);
		int num = 1;
		if (numOfPlayers == 0) {
			Text warning = new Text(prop.getProperty("noplayerwarning"));
			warning.setId("light");
			myContent.getChildren().add(warning);
		} else {

			while (numOfPlayers >= num) {
				playerContent = new PlayerContent(num, controller, prop);
				playerContentList.add(playerContent);
				dialogElements = new DialogElements(prop, controller);
				playerContent.setDialogElements(dialogElements);
				playerContent.init();
				myContent.getChildren().add(playerContent);
				num++;
			}
			this.getDialogPane().getButtonTypes().add(mySave);
		}

		myContent.setId("vbox-element");
	}

	@Override
	protected void setSaveAction() {
		this.setResultConverter(dialogButton -> {
			if (dialogButton == mySave) {
				for (int i = 0; i < playerContentList.size(); i++) {
					PlayerContent currPlayerContent = playerContentList.get(i);
					int id = currPlayerContent.getPlayerId();
					String name = currPlayerContent.getPlayerName();
					int numMoves = currPlayerContent.getNumMoves();
					controller.addPlayerToList(name, id);
					// Save Player
					PlayerParams params = new PlayerParams(id, name, numMoves);
					controller.savePlayer(params);

					Reflection reflection = new Reflection();
					List<ObjParam> allObjParams = controller
							.getPropertiesInterface().getAllPlayerCharParams();

					String className = gaeDialogPath + "PlayerCharDialog";
					
//					try {
//						Class c = Class.forName(name2);
//						Constructor con = c.getConstructor(List.class, IDialogGaeController.class, int.class);
//						con.newInstance(allObjParams, controller, i);						
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					Reflection
							.createInstance(className, allObjParams, controller, i);
				}
			}
			return null;
		});
	}
}
