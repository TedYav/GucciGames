package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ActionPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsDialog extends javafx.scene.control.Dialog implements ISwitchSettingsPane{
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;	
	private IDialogGaeController controller;
	private Properties prop;
	private DialogElements dialogElements;
	private ActionPane actionPane;
	private static GaeDialogHelper helper = new GaeDialogHelper();
	private ScrollPane scrollPane = new ScrollPane();
	
	
	public SettingsDialog(IDialogGaeController controller){
		super();
		prop = helper.loadProperties("dialogproperties/actionsettings.properties");			
		this.controller = controller;
		dialogElements = new DialogElements(prop, controller);	
		this.setHeaderText("Settings");
		scrollPane.setContent(actionPane);
		this.getDialogPane().setContent(scrollPane);
		this.getDialogPane().setPrefSize(WIDTH, HEIGHT);
		this.init();		
	}
		
	public void init(){
		actionPane = new ActionPane(dialogElements, this);
		this.getDialogPane().setContent(actionPane);
		this.getDialogPane().getButtonTypes().setAll(ButtonType.CLOSE);
		

	}

	@Override
	public void switchSettingsPane(Node n) {
			scrollPane.setContent(n);
			this.getDialogPane().setContent(scrollPane);
	}


	@Override
	public Optional<ButtonType> getDialogButtonResponse() {
		// TODO Auto-generated method stub
		return this.showAndWait();
	}


	
	


}
