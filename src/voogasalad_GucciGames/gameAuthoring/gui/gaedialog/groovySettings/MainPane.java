package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.RadioBtnField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainPane extends GridPane{
	
	private ISwitchGroovyPane groovyPaneController;
	private ISwitchSettingsPane settingsPaneController;
	
	private static final String groovyPackagePath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	private static final String settingsPackagePath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.";
	private Button nextBtn = new Button("Next");
	
	
	private Text title;
	
	private String selected;
	private MapObjectType type;

	
	private List<String> items = new ArrayList<String>();
	private RadioBtnField radioBtnField;
	private Properties prop;
	private IDialogGaeController dialogController;
	
	public MainPane(ISwitchSettingsPane settingsPaneController, 
			Properties prop, IDialogGaeController dialogController , MapObjectType type){
		title = new Text("I want to add a new ...");
		this.type = type;
		this.settingsPaneController = settingsPaneController;
		this.prop = prop;
		this.dialogController = dialogController;
		items.add("Action");
		items.add("Characteristic");	
		radioBtnField = new RadioBtnField(items);
		addActionForSettingsNextBtn();
		setLayout();
		
		
	}
	
	
	public MainPane(ISwitchGroovyPane groovyPaneController){
		title = new Text("I want to create a new ...");
		this.groovyPaneController = groovyPaneController;
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
			groovyPaneController.switchGroovyPane(
					reflection.createInstance(name,  selected, groovyPaneController), "Custom " + selected);
		});
		
	}
	
	private void addActionForSettingsNextBtn(){
		Reflection reflection = new Reflection();
		nextBtn.setOnAction(e -> {
			selected = radioBtnField.getSelected();	
			String name = settingsPackagePath + selected + "Pane";
			settingsPaneController.switchSettingsPane(
					reflection.createInstance(name, settingsPaneController, dialogController, prop, type));
		});
	}
	

	
	

}
