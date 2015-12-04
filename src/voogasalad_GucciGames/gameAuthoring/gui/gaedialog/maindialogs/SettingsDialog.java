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
	private ButtonType controlBtn = new ButtonType("Next", ButtonData.NEXT_FORWARD);
	private static GaeDialogHelper helper = new GaeDialogHelper();
	
	
	public SettingsDialog(IDialogGaeController controller){
		super();
		prop = helper.loadProperties("dialogproperties/actionsettings.properties");			
		this.controller = controller;
		dialogElements = new DialogElements(prop, controller);	
		this.setHeaderText("Settings");
		this.getDialogPane().setContent(actionPane);
		this.getDialogPane().setPrefSize(WIDTH, HEIGHT);
		this.init();		
	}
		
	public void init(){
		actionPane = new ActionPane(dialogElements, this);
		//actionPane.setPrefSize(WIDTH, HEIGHT);
		System.out.println("pane: " +this.getDialogPane());
		this.getDialogPane().setContent(actionPane);
		final ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll( cancelBtn, controlBtn);
		

	}

	@Override
	public void switchSettingsPane(Node n) {
			this.getDialogPane().setContent(n);	
	}

	@Override
	public void setControlBtn(ButtonType btn) {
		controlBtn = btn;
	}

	@Override
	public Optional<ButtonType> getDialogButtonResponse() {
		return this.showAndWait();
	}
	
	


}
