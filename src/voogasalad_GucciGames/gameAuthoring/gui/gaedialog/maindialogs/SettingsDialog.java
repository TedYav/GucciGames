package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.MainPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ActionPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsDialog extends AGaeDialog implements ISwitchSettingsPane{

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;	
	private IDialogGaeController controller;
	private Properties prop;
	private static GaeDialogHelper helper = new GaeDialogHelper();
	private ScrollPane scrollPane = new ScrollPane();
	private MainPane mainPane;


	public SettingsDialog(IDialogGaeController controller){
		super();
		prop = helper.loadProperties("dialogproperties/actionsettings.properties");			
		this.controller = controller;
		this.setHeaderText("Settings");
		this.mainPane = new MainPane(this, prop);
		scrollPane.setContent(mainPane);
		this.getDialogPane().setContent(scrollPane);
		this.getDialogPane().setPrefSize(WIDTH, HEIGHT);
	}

	@Override
	public void switchSettingsPane(Object n) {
		scrollPane.setContent((Pane) n);
		this.getDialogPane().setContent(scrollPane);
	
	}


	@Override
	public Optional<ButtonType> getDialogButtonResponse() {
		// TODO Auto-generated method stub
		return this.showAndWait();
	}






}
