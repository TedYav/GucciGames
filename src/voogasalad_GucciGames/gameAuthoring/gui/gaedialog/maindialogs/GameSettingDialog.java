package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.RadioBtnField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ScrollBarField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class GameSettingDialog extends GaeDialog {
	
	private VBox myContent = new VBox();
	private Properties prop;
	private IDialogGaeController dialogGaeController;
	private DialogElements dialogElements;
	private GameSettingParams gameSettingParams = new GameSettingParams();
	private TextInputField nameText;
	private DropDownMenuField mapSize;
	private DropDownMenuField fogOfWar;
	private RadioBtnField miniMap ;
	private RadioBtnField zoomable;
	private ScrollBarField numPlayer;
	
	
	public GameSettingDialog(IDialogGaeController dialogGaeController){
		this.dialogGaeController = dialogGaeController;
		prop = super.loadProperties("/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/maindialogs/dialogproperties/gamedialog.properties");
		dialogElements = new DialogElements(prop, dialogGaeController);
		myContent.getChildren().addAll(this.initializeDialog());
		this.getDialogPane().setContent(myContent);
	}
	
	

	protected VBox initializeDialog() {
		VBox content = new VBox();				
		Text titleElement = new Text();
		titleElement.setText(prop.getProperty("title"));
		nameText = new TextInputField(dialogElements, "name");
		mapSize = new DropDownMenuField(dialogElements, "mapsize", "mapsize_items", null);
		 fogOfWar = new DropDownMenuField(dialogElements, "fogofwar", "fogofwar_items", null);
		miniMap = new RadioBtnField(dialogElements, "minimap", "minimap_items");
		 zoomable = new RadioBtnField(dialogElements, "zoomable", "zoomable_items");
		numPlayer = new ScrollBarField(dialogElements, "numplayer", "numplayer_items");	
		
		content.getChildren().addAll(titleElement, nameText, mapSize, fogOfWar,
				miniMap, zoomable, numPlayer);		
		content.getChildren().forEach(hbox->hbox.setId("hbox-element"));		
		titleElement.setId("title");
		content.setId("vbox-element");	
		return content;
	}
	


}
