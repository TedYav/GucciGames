package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ScrollBarField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.GaeDialogHelper;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameSettingsPane extends GridPane {
	
	private Properties prop;
	private TextInputField nameText;
	private DropDownMenuField fogOfWar;
	private ScrollBarField numPlayer;
	
	public GameSettingsPane(){
		GaeDialogHelper helper = new GaeDialogHelper();
		prop = helper.loadProperties("/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/maindialogs/dialogproperties/gamedialog.properties");
		init();
	}
	
	private void init(){
		Text titleElement = new Text();
		titleElement.setText(prop.getProperty("title"));
		nameText = new TextInputField(prop, "name");
		fogOfWar = new DropDownMenuField(prop, "fogofwar", "fogofwar_items");
		numPlayer = new ScrollBarField(prop, "numplayer", "numplayer_items");
		this.getChildren().add(titleElement);
		this.add(nameText, 0, 1);
		this.add(fogOfWar, 0, 2);
		this.add(numPlayer, 0, 3);
		
	}
	
	public int getNumPlayers(){
		return (int) numPlayer.getSelectedDouble();
	}

}
