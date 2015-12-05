package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ScrollBarField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.GameSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;


public class GameSettingDialog extends Dialog {

	private VBox myContent = new VBox();
	private Properties prop;
	private IDialogGaeController dialogGaeController;
	private DialogElements dialogElements;
	private GameSettingParams gameSettingParams = new GameSettingParams();
	private TextInputField nameText;
	private DropDownMenuField fogOfWar;
	private ScrollBarField numPlayer;


	public GameSettingDialog(IDialogGaeController dialogGaeController){
		GaeDialogHelper helper = new GaeDialogHelper();
		this.dialogGaeController = dialogGaeController;
		prop = helper.loadProperties("/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/maindialogs/dialogproperties/gamedialog.properties");
		dialogElements = new DialogElements(prop, dialogGaeController);
		GameSettingsPane pane = new GameSettingsPane();
		this.getDialogPane().setContent(pane);
		final ButtonType save = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().addAll(save, ButtonType.CLOSE);
	}



}
