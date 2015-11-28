package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.Properties;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.AParamsObject;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;

public class SaveField extends DialogComponent {
	
	private DialogElements dialogElements;
	private IDialogGaeController dialogGaeController;
	private HBox content = new HBox();
	private Button saveBtn;
	private Stage stage;
	private AParamsObject params;
	
	public SaveField(DialogElements dialogElements, IDialogGaeController dialogGaeController, Stage s ){
		this.dialogElements = dialogElements;
		this.dialogGaeController = dialogGaeController;
		stage = s;		
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
		saveBtn.setOnAction(e ->  {
			//dialogGaeController.createCustomMapObject(dialogElements.getObjectProperty());
			//dialogGaeController.saveGameSetting(gameParams);
			stage.close();
			});
	}
	
	public Button getSaveBtn(){
		return saveBtn;
	}


	@Override
	public void setSelected(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSelected() {
		// TODO Auto-generated method stub
		return null;
	}

}
