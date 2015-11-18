package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerContent extends DialogContent{
	
	private VBox content = new VBox();
	
	TextInputField textInputField;
	FileBrowserField browserField;
	
	private DialogElements dialogElements;
	
	private int playerNumber;
	
	public PlayerContent(int playerNumber){
		this.playerNumber = playerNumber;
		//init();
	}
	
	protected void init(){
		Text title = new Text ("Player " + playerNumber);
		title.setId("subtitle");
		textInputField = new TextInputField(dialogElements, 
				"player"+playerNumber);
		browserField = new FileBrowserField(dialogElements,"image", "browse", "filechoosertitle");
		content.getChildren().addAll(title, textInputField.getContent(), browserField.getContent());
		content.setId("vbox-element");
	}

	@Override
	protected VBox getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	protected void setDialogElements(DialogElements dialogElements) {
		// TODO Auto-generated method stub
		this.dialogElements = dialogElements;
		
	}

	@Override
	protected void initializeCustomContent() {
		// TODO Auto-generated method stub
		
	}



}
