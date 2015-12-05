package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import voogasalad.util.reflection.Reflection;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ActionPane extends GridPane {
	
	private Label nameLbl;
	
	private Label actionLbl = new Label("Action: ");
	private TextArea action = new TextArea();
	
	
	private Label requestLbl = new Label("Request: ");
	private TextArea request = new TextArea();
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";

	private ISwitchGroovyPane controller;
	
	public ActionPane(String name, ISwitchGroovyPane controller){
		super();
		this.controller = controller;
		nameLbl = new Label(name + " Action");
		nameLbl.setFont( new Font("Arial", 20));
		this.setHalignment(nameLbl, HPos.RIGHT);
		this.setHgap(5);
		this.setVgap(5);
		this.add(nameLbl, 0, 0);
		init();
		
		
	}
	
	void init(){
		this.add(actionLbl, 0, 1);
		this.add(action, 1, 1);
		this.add(requestLbl, 0, 2);
		this.add(request, 1, 2);
		final Button nextBtn = new Button("Next");
		nextBtn.setOnAction(e -> {
			Reflection reflection = new Reflection();
			controller.switchGroovyPane(reflection.createInstance(path + "AddRuleToActionPane"),
					"Add Rule(s) to Action");
		});
		this.add(nextBtn, 3,3);
			
	}

}
