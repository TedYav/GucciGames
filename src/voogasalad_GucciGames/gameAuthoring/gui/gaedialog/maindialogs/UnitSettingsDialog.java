package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.Properties;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ScrollBarField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.UnitParams;
import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.Property;

public class UnitSettingsDialog extends GaeDialog{
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;	
	private VBox myContent = new VBox();	
	private Stage unitSettingDialog = new Stage();
	private ObjectProperty objProp= new ObjectProperty();
	private IDialogGaeController controller;
	private Properties prop;
	private DialogElements dialogElements;
	private Scene scene;
	
	private String name;
	private int numAttack;
	private int damage;
	private int healthVal;
	private int rangeMvt;
	private int rangeAttack;
	
	private TextInputField nameInputField;
	
	private ScrollBarField numAttackField;
	private ScrollBarField damageField;
	private ScrollBarField healthField;
	private ScrollBarField rangeMvtField;
	private ScrollBarField rangeAttackField;
	
	private Button saveBtn = new Button("save");

	
	
	
	public UnitSettingsDialog(IDialogGaeController controller){
		prop = loadProperties(
				"/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/maindialogs/dialogproperties/unitsettingdialog.properties");
		
		this.controller = controller;
		objProp = new ObjectProperty();

		this.dialogElements = new DialogElements(prop, null, controller);
		//dialogElements.getSaveObjProperty().saveObjProperty("type", "playersetting");
		initialize();
		addActionToSave();
		setScene();	
	}
	
	private void setScene(){

		scene = new Scene(myContent, WIDTH, HEIGHT);		
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		unitSettingDialog.setScene(scene);	

	}
	
	private void initialize(){
		Text title = new Text("Set Unit Properties");
		title.setId("title");
		nameInputField = new TextInputField(dialogElements, "name");
		numAttackField = new ScrollBarField(dialogElements, "numattack", "numattack_items");
		damageField = new ScrollBarField(dialogElements, "damage", "damage_items");
		healthField = new ScrollBarField(dialogElements, "health", "health_items");
		rangeMvtField = new ScrollBarField(dialogElements, "mvtrange", "mvtrange_items");
		rangeAttackField = new ScrollBarField(dialogElements, "attackrange", "attackrange_items");
		myContent.getChildren().addAll(title, nameInputField, numAttackField, damageField, 
				healthField, rangeMvtField, rangeAttackField, saveBtn);
		myContent.setId("vbox-element");

		
	}
	
	private void addActionToSave(){
		saveBtn.setOnAction(e -> {
			name = nameInputField.getTextInput();
			numAttack = (int) numAttackField.getSelectedDouble();
			damage = (int)damageField.getSelectedDouble();
			healthVal = (int)healthField.getSelectedDouble();
			rangeMvt = (int) rangeMvtField.getSelectedDouble();
			rangeAttack = (int) rangeAttackField.getSelectedDouble();
			
			UnitParams unitParams = new UnitParams(name, numAttack, damage, healthVal, rangeMvt, rangeAttack);
			controller.setUnitParams(unitParams);
			this.unitSettingDialog.close();
			
		});
	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		return null;
	}
	public void show(){
		this.unitSettingDialog.show();
	}
	
	

}
