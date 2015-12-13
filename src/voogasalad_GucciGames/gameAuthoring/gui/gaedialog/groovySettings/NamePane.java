package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;

public class NamePane extends GridPane {
	
	private Label instructionsLbl;
	private Label textFieldLbl;
	private TextField textField;
	private Button nextBtn;
	private String type;
	private ISwitchGroovyPane paneController;
	private IDialogGaeController dialogController;
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	
	public NamePane(String type, ISwitchGroovyPane paneController
			, IDialogGaeController dialogController){
		this.paneController = paneController;
		this.dialogController = dialogController;
		this.type = type;
		instructionsLbl = new Label(" Please enter in a name ");
		instructionsLbl.setFont(new Font("Arial", 20));
		textFieldLbl = new Label("Name: ");
		textField = new TextField();
		nextBtn = new Button("Next");
		this.getChildren().add(instructionsLbl);
		this.add(textFieldLbl, 0, 1);
		this.add(textField, 1, 1);
		this.add(nextBtn, 2, 2);
		this.setPadding(new Insets(50, 50, 50, 50));
		this.setVgap(10);
		this.setHgap(10);
		init();
	}
	
	void init(){
		nextBtn.setOnAction(e -> {
			if(validate()){
				Reflection reflection = new Reflection();
				paneController.switchGroovyPane(reflection.createInstance(
						path + type + "Pane", textField.getText(), paneController,  dialogController), 
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
