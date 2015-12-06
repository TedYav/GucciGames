package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.List;
import java.util.Properties;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.FileBrowserField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ScrollBarField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerContent extends GridPane{
	private static final String gaeDialogPath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.";
	private static final String settingsPackagePath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.";

	TextInputField textInputField;
	FileBrowserField browserField;
	
	private DialogElements dialogElements;
	private Properties prop;
	
	private int playerNumber;
	
	private ScrollBarField scrollBarField;
	
	private IDialogGaeController controller;
	
//	private Button nextBtn = new Button("Next");
	
	
	public PlayerContent(int playerNumber, IDialogGaeController controller, Properties prop){
		this.playerNumber = playerNumber;
		this.controller = controller;
		this.prop = prop;
		init();
//		addNextBtnAction();
	}
	
//	private void addNextBtnAction() {
//		// TODO Auto-generated method stub
//		Reflection reflection = new Reflection();
//		nextBtn.setOnAction(e -> {
//			List<ObjParam> allObjParams = controller.getAllPlayerCharParams();
//			
//			String name = settingsPackagePath + "PlayerCharDialog";
//			reflection.createInstance(name, allObjParams);
//		});
//	}

	protected void init(){
		Text title = new Text ("Player " + playerNumber);
		title.setId("subtitle");
		textInputField = new TextInputField(prop, 
				"player"+playerNumber);
		
		browserField = new FileBrowserField(prop,"image", "browse", "filechoosertitle");
		scrollBarField = new ScrollBarField(prop, "nummoves", "nummoves_items");
		scrollBarField.addListenerForPlayer();
		
		this.getChildren().add(title);
		
		this.add(textInputField, 0, 1);
		this.add(browserField, 0, 2);
		this.add(scrollBarField, 0, 3);	
//		this.setHalignment(nextBtn, HPos.RIGHT);
//		this.add(nextBtn, 0, 4);
	}
	
	protected String getPlayerName(){
		return textInputField.getTextInput();
	}
	
	protected int getPlayerId(){
		return playerNumber;
	}
	
	protected String getPlayerImagePath(){
		return browserField.getPath();
	}
	
	protected int getNumMoves(){
		 return (int) scrollBarField.getSelectedDouble();
	}

	
	public void setDialogElements(DialogElements dialogElements) {
		this.dialogElements = dialogElements;
		
	}



}
