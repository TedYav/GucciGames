package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerContent extends DialogContent{
	
	private VBox content = new VBox();
	
	TextInputField textInputField;
	FileBrowserField browserField;
	
	private DialogElements dialogElements;
	
	private int playerNumber;
	
	private IDialogGaeController controller;
	
	private Button saveBtn; 
	
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
		
		content.getChildren().addAll(title, textInputField.getContent(), browserField.getContent());
		initializeSaveBtn();
		content.setId("vbox-element");
	}
	
	private void initializeSaveBtn(){
		saveBtn = new Button(dialogElements.getDialogProperties().getProperty("savebtn"));
		saveBtn.setOnAction(e -> {
			controller.addPlayerToList(textInputField.getTextInput(), playerNumber);		
		});
		content.getChildren().add(saveBtn);
		
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
