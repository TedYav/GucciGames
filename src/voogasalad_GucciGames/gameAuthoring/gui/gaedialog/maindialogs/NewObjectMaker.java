package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class NewObjectMaker extends javafx.scene.control.Dialog{	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private Properties prop;
	private IDialogGaeController controller;
	private GridPane gridPane = new GridPane();
	
	
	public NewObjectMaker( IDialogGaeController controller){
		super();
		GaeDialogHelper helper = new GaeDialogHelper();
		prop = helper.loadProperties("dialogproperties/tiledialogproperties.properties");	
		this.controller = controller;
		DialogElements dialogElements = new DialogElements(prop, controller);	
		gridPane = new NewObjMakerPane(prop);		
		this.getDialogPane().setContent(gridPane);
		final ButtonType save = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().addAll(save, ButtonType.CANCEL);
	 }	
 
	 

}
