package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.Properties;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;

public class SaveField extends DialogComponent {
	
	private DialogElements dialogElements;
	private IDialogGaeController dialogGaeController;
	private HBox content = new HBox();
	private Button saveBtn;
	
	public SaveField(DialogElements dialogElements, IDialogGaeController dialogGaeController ){
		this.dialogElements = dialogElements;
		this.dialogGaeController = dialogGaeController;
		initializeControl();
	}
	
	protected void initializeControl(){
		content.setId("hbox-control");
		saveBtn = new Button(dialogElements.getDialogProperties().getProperty("savebtn"));
		addListenerToSaveBtn();
		content.getChildren().add(saveBtn);	
		content.setId("hbox-element");
	}
	private void addListenerToSaveBtn(){
		saveBtn.setOnAction(e -> dialogGaeController.createCustomMapObject(dialogElements.getObjectProperty()));
	}

	@Override
	HBox getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	void setSelected(String s) {
		// TODO Auto-generated method stub
		
	}

}
