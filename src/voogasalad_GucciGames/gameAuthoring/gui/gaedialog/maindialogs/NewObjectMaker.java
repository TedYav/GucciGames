package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewObjectMaker extends GaeDialog{	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private Properties prop;
	private IDialogGaeController controller;
	private GridPane gridPane = new GridPane();
	
	
	public NewObjectMaker( IDialogGaeController controller){
		super();
		prop = loadProperties("dialogproperties/tiledialogproperties.properties");	
		this.controller = controller;
		DialogElements dialogElements = new DialogElements(prop, controller);	
		gridPane = new NewObjMakerPane(dialogElements);				 
	 }	
 
	 

}
