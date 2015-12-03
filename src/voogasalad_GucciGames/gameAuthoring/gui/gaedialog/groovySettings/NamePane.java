package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import voogasalad.util.reflection.Reflection;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class NamePane extends GridPane {
	
	private Label instructionsLbl;
	private Label textFieldLbl;
	private TextField textField;
	private Button nextBtn;
	private String type;
	private ISwitchGroovyPane paneController;
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	
	public NamePane(String type, ISwitchGroovyPane paneController){
		this.paneController = paneController;
		this.type = type;
		instructionsLbl = new Label(" Please enter in a name ");
		instructionsLbl.setFont(new Font("Arial", 20));
		textFieldLbl = new Label("Name: ");
		textField = new TextField();
		nextBtn = new Button("Next");
		this.add(instructionsLbl, 0, 0);
		this.add(textFieldLbl, 0, 1);
		this.add(textField, 1, 1);
		this.add(nextBtn, 2, 2);
		this.setPadding(new Insets(50, 50, 50, 50));
		init();
	}
	
	void init(){
		nextBtn.setOnAction(e -> {
			if(validate()){
				Reflection reflection = new Reflection();
				paneController.switchGroovyPane(reflection.createInstance(
						path + type + "Pane", textField.getText()), 
						"Custom " + type);
			}
			
		});
	}
	
	private boolean validate(){
		try{
			String name = textField.getText();
			return (name != "");
		} catch(Exception e){
			nextBtn.setDisable(true);
			return false;
		}
		
		
	}

}
