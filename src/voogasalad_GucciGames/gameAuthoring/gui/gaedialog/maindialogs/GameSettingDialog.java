package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.Properties;

import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ScrollBarField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;


import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;

public class GameSettingDialog extends AGaeDialog {
	private VBox myContent = new VBox();
	private Properties prop;
	private IDialogGaeController dialogGaeController;
	private DialogElements dialogElements;
	private GameSettingParams gameSettingParams = new GameSettingParams();
	// private TextInputField nameText;
	private DropDownMenuField fogOfWar;
	// private ScrollBarField numPlayer;
	private GameSettingsPane myPane;
	// private final ButtonType mySave = new ButtonType("Save",
	// ButtonData.FINISH);

	public GameSettingDialog(IDialogGaeController dialogGaeController) {
		super();
		GaeDialogHelper helper = new GaeDialogHelper();
		this.dialogGaeController = dialogGaeController;
		prop = helper.loadProperties(
				"/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/maindialogs/dialogproperties/gamedialog.properties");
		dialogElements = new DialogElements(prop, dialogGaeController);
		myPane = new GameSettingsPane();
		this.getDialogPane().setContent(myPane);
		this.getDialogPane().getButtonTypes().add(mySave);
		setSaveAction();
	}

	@Override
	protected void setSaveAction() {
		this.setResultConverter(dialogButton -> {
			if (dialogButton == mySave) {
				System.out.println(myPane.getNumPlayers());
				dialogGaeController.setNumberOfPlayers(myPane.getNumPlayers());
				this.close();
			}
			return null;
		});
	}

}
