package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.Properties;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.CheckBoxField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;

public class CustomGPlayerDialog extends GaeDialog{

	private Stage gameSettingDialog = new Stage();
	private VBox myContent = new VBox();
	private Properties prop;
	private IDialogGaeController dialogGaeController;
	private IGuiGaeController guiGaeController;
	private DialogElements dialogElements;
	private GameSettingParams gameSettingParams = new GameSettingParams();
	private CheckBoxField actionDisplay;
	private CheckBoxField displayChat;
	private CheckBoxField displayMapObjectDetails;
	private CheckBoxField displayMapObjectImage;
	private CheckBoxField displayGameStats;
	private Button saveButton;
	
	private ArrayList<CheckBoxField> allCheckBoxFields = new ArrayList<CheckBoxField>();
	private ArrayList<String> checkedBoxes = new ArrayList<String>();	
	
	public CustomGPlayerDialog(IDialogGaeController dialogGaeController, IGuiGaeController guiGaeController){
		this.dialogGaeController = dialogGaeController;
		this.guiGaeController = guiGaeController;
		prop = super.loadProperties("/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/maindialogs/dialogproperties/customgplayerdialog.properties");
		dialogElements = new DialogElements(prop, null, dialogGaeController);
		saveButton = new Button("Save");
		setSaveAction();
		myContent.getChildren().addAll(this.initializeDialog(), saveButton);
		Scene gameSettingDialogScene = new Scene(myContent, 500, 500);
		gameSettingDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		gameSettingDialog.setScene(gameSettingDialogScene);		
	}
	
	protected void setSaveAction(){
		saveButton.setOnAction(e -> {
			System.out.println(checkedBoxes);
			guiGaeController.setCustomGamePlayerComponents(checkedBoxes);
			this.gameSettingDialog.close();
		});
	}
	

	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		VBox content = new VBox();				
		Text titleElement = new Text();
		titleElement.setText(prop.getProperty("title"));
		
		actionDisplay = new CheckBoxField(dialogElements, "ActionDisplay");
		allCheckBoxFields.add(actionDisplay);
		displayChat = new CheckBoxField(dialogElements, "DisplayChat");
		allCheckBoxFields.add(displayChat);
		displayMapObjectDetails = new CheckBoxField(dialogElements, "DisplayMapObjectDetails");
		allCheckBoxFields.add(displayMapObjectDetails);
		displayMapObjectImage = new CheckBoxField(dialogElements, "DisplayMapObjectImage");
		allCheckBoxFields.add(displayMapObjectImage);
		displayGameStats = new CheckBoxField(dialogElements, "GameStatsDisplay");
		allCheckBoxFields.add(displayGameStats);
		setCheckBoxListeners();
		
		content.getChildren().addAll(titleElement, actionDisplay, displayChat, displayMapObjectDetails, displayMapObjectImage, displayGameStats);		
		content.getChildren().forEach(hbox->hbox.setId("hbox-element"));		
		titleElement.setId("title");
		content.setId("vbox-element");	
		return content;
	}
	
	

	public void showCustomGPlayerDialog(){
		super.showDialog(gameSettingDialog);
	}

	private void setCheckBoxListeners(){
		for(int i=0; i<allCheckBoxFields.size(); i++){
			CheckBoxField currCheckBoxField = allCheckBoxFields.get(i);
			currCheckBoxField.getCheckBox().selectedProperty().addListener((new ChangeListener<Boolean>() {
				public void changed(ObservableValue<? extends Boolean> ov,
						Boolean old_val, Boolean new_val) {
					if(new_val){
						checkedBoxes.add(currCheckBoxField.getPropKey());
					}else{
						checkedBoxes.remove(currCheckBoxField.getPropKey());
					}
				}
			}));
		}
	}

}
