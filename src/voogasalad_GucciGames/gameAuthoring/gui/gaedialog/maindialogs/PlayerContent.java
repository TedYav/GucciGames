package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.FileBrowserField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ScrollBarField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerContent extends VBox{
	
	
	TextInputField textInputField;
	FileBrowserField browserField;
	
	private DialogElements dialogElements;
	
	private int playerNumber;
	
	private ScrollBarField scrollBarField;
	
	private IDialogGaeController controller;
	
	
	public PlayerContent(int playerNumber, IDialogGaeController controller){
		this.playerNumber = playerNumber;
		this.controller = controller;
		//init();
	}
	
	protected void init(){
		Text title = new Text ("Player " + playerNumber);
		title.setId("subtitle");
		textInputField = new TextInputField(dialogElements, 
				"player"+playerNumber);
		browserField = new FileBrowserField(dialogElements,"image", "browse", "filechoosertitle");
		scrollBarField = new ScrollBarField(dialogElements, "nummoves", "nummoves_items");
		scrollBarField.addListenerToScrollBar();
		
		this.getChildren().addAll(title, textInputField, browserField, scrollBarField);
		this.setId("vbox-element");
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
		// TODO Auto-generated method stub
		this.dialogElements = dialogElements;
		
	}



}
