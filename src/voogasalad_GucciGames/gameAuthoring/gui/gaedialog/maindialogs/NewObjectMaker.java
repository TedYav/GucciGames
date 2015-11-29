package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveGroovy;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.SaveField;
import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewObjectMaker extends GaeDialog{	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private GroovyTabPane groovyTabPane;
	private VBox myContent = new VBox();
	private Stage makerDialog = new Stage();
	private Map<String, String> groovyBuffer = new HashMap<String, String>();
	private Properties prop;
	private ISaveGroovy saveGroovy;
	private IDialogGaeController controller;
	private DialogElements dialogElements;
	private Scene scene;
	private SaveField save;
	
	
	public NewObjectMaker( IDialogGaeController controller){
		super();
		prop = loadProperties("dialogproperties/tiledialogproperties.properties");	
		saveGroovy = setSaveGroovyFunctions(groovyBuffer, saveGroovy);
		this.controller = controller;
		dialogElements = new DialogElements(prop, saveGroovy, controller);
		groovyTabPane = new GroovyTabPane(dialogElements);
		save = new SaveField(dialogElements, controller, makerDialog);

		
		MakerDialog dialog = new MakerDialog(dialogElements,  groovyTabPane, save);			
		myContent = dialog;
		myContent.setId("vbox-element");
		
		scene = new Scene(myContent, WIDTH, HEIGHT);
		
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/tab.css");
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/groovytab.css");
		makerDialog.setScene(scene);		
		 
	 }	

	 
	 protected int getIdForTab(Tab t){
		 return Integer.parseInt(t.getText().split("\\s+")[1]);
	 }
	 	 
	 
		public void showDialog(){
			super.showDialog(makerDialog);
		}


		@Override
		protected VBox initializeDialog() {
			// TODO Auto-generated method stub
			return null;
		}
	 

}
