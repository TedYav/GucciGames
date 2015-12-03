package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.RadioBtnField;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainPane extends GridPane{
	
	private ISwitchGroovyPane paneController;
	
	private static final String packagePath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	
	private Button nextBtn = new Button("Next");
	
	
	private Text title = new Text("I want to create a new ...");
	
	private String selected;

	
	private List<String> items = new ArrayList<String>();
	private RadioBtnField radioBtnField;
	
	
	public MainPane(ISwitchGroovyPane paneController, int width, int height){
		this.paneController = paneController;
		this.setPrefSize(width, height);
		
		items.add("Action");
		items.add("Characteristic");
		items.add("Condition");
		items.add("Rule");
		items.add("Outcome");
		
		radioBtnField = new RadioBtnField(items);
		
		this.setHalignment(title, HPos.CENTER);
		this.add(title, 0, 0);
		
		this.setHalignment(radioBtnField, HPos.CENTER);
		this.setValignment(radioBtnField, VPos.CENTER);
		this.add(radioBtnField, 0, 1);
		
		this.setHalignment(nextBtn, HPos.RIGHT);
		this.add(nextBtn, 1, 2);
		
		addActionForNextBtn();
		
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(20, 20, 20, 20));
	}
	
	private void addActionForNextBtn(){
		//groovySettings.CharacteristicsPane
		Reflection reflection = new Reflection();
		nextBtn.setOnAction(e -> {
			selected = radioBtnField.getSelected();
	
			String name = packagePath + "NamePane";
			paneController.switchGroovyPane(reflection.createInstance(name,  selected, paneController), "Custom " + selected);
		});
	}
	

	
	

}
