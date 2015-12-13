package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.RadioBtnField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class MainPane extends GridPane{
	
	private ISwitchGroovyPane groovyPaneController;
	private ISwitchSettingsPane settingsPaneController;
	
	private static final String groovyPackagePath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	private static final String settingsPackagePath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.";
	private Button nextBtn = new Button("Next");
	
	
	private Text title;
	
	private String selected;
	private MapObjectType mapObjectType;

	
	private List<String> items = new ArrayList<String>();
	private RadioBtnField radioBtnField;
	private Properties prop;
	private IDialogGaeController dialogController;
	
	private ActionParamsValue actionParamsValue;
	private List<ObjParamValue> charParamValues;
	
	public MainPane(ISwitchSettingsPane settingsPaneController,
			Properties prop, IDialogGaeController dialogController , MapObjectType type, ActionParamsValue actionParamsValue, List<ObjParamValue> objParamValue){
		title = new Text("I want to add a new ...");
		
		this.mapObjectType = type;
		
		this.settingsPaneController = settingsPaneController;
		this.prop = prop;
		this.dialogController = dialogController;
		this.actionParamsValue = actionParamsValue;
		this.charParamValues = objParamValue;
		items.add("Action");
		items.add("Characteristic");	
		radioBtnField = new RadioBtnField(items);
		addActionForSettingsNextBtn();
		setLayout();
		
		
	}
	
	
	public MainPane(ISwitchGroovyPane groovyPaneController, IDialogGaeController dialogController){
		title = new Text("I want to create a new ...");
		this.groovyPaneController = groovyPaneController;
		this.dialogController = dialogController;
		//this.setPrefSize(width, height);		
		items.add("Action");
		items.add("Characteristic");
		items.add("Condition");
		items.add("Rule");
		items.add("Outcome");	
		radioBtnField = new RadioBtnField(items);
		addActionForGroovyNextBtn();	
		setLayout();

	}

	
	private void setLayout(){
		this.setHalignment(title, HPos.CENTER);
		this.add(title, 0, 0);	
		this.setHalignment(radioBtnField, HPos.CENTER);
		this.setValignment(radioBtnField, VPos.CENTER);
		this.add(radioBtnField, 0, 1);
		this.setHalignment(nextBtn, HPos.RIGHT);
		this.add(nextBtn, 1, 2);		
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(20, 20, 20, 20));
		
	}
	
	private void addActionForGroovyNextBtn(){
		Reflection reflection = new Reflection();
		nextBtn.setOnAction(e -> {
			selected = radioBtnField.getSelected();
			String name = groovyPackagePath + "NamePane";
			System.out.println("groovy: " + this.groovyPaneController);
			groovyPaneController.switchGroovyPane(
					Reflection.createInstance(name,  selected, groovyPaneController, dialogController), "Custom " + selected);
		});
		
	}
	
	private void addActionForSettingsNextBtn(){
		Reflection reflection = new Reflection();
		nextBtn.setOnAction(e -> {
			selected = radioBtnField.getSelected();	
			String name = settingsPackagePath + selected + "Pane";
			System.out.println("map obj: " + this.mapObjectType);
			if (selected.equals("Action")){
				settingsPaneController.switchSettingsPane(
						Reflection.createInstance(name, settingsPaneController,
								dialogController, prop, mapObjectType, this.actionParamsValue));
			} else {
				settingsPaneController.switchSettingsPane(
						reflection.createInstance(name, settingsPaneController,
								dialogController, prop, mapObjectType, this.charParamValues));
			}
			
			
			
		});
	}
	

	
	

}
