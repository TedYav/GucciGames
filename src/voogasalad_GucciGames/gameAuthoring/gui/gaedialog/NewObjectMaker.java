package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
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
	private Stage unitMakerDialog = new Stage();
	private Map<String, String> groovyBuffer = new HashMap<String, String>();
	private Properties prop;
	private ObjectProperty unitProperty = new ObjectProperty();
	private ISaveGroovy saveGroovy;
	private ISaveObjProperty saveObjProperty;
	private ISaveCustomObj saveCustomObj;
	private DialogElements dialogElements;
	private Scene scene;
	private SaveField save;
	
	
	public NewObjectMaker(DialogContent customContent, IDialogGaeController controller){
		super();
		prop = loadProperties("dialogproperties/tiledialogproperties.properties");	
		saveGroovy = setSaveGroovyFunctions(groovyBuffer, saveGroovy);
		saveObjProperty = setSavePropertyFunction(unitProperty, saveObjProperty);		
		dialogElements = new DialogElements(prop, unitProperty, saveObjProperty, saveGroovy, saveCustomObj);
		groovyTabPane = new GroovyTabPane(dialogElements);
		save = new SaveField(dialogElements, controller);
		customContent.setDialogElements(dialogElements);
		customContent.initializeCustomContent();
		
		MakerDialog dialog = new MakerDialog(dialogElements, customContent, groovyTabPane, save);			
		myContent = dialog.getContent();
		
		scene = new Scene(myContent, WIDTH, HEIGHT);
		
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		unitMakerDialog.setScene(scene);		
		 
	 }	

	 
	 protected int getIdForTab(Tab t){
		 return Integer.parseInt(t.getText().split("\\s+")[1]);
	 }
	 	 
	 
		public void showDialog(){
			super.showDialog(unitMakerDialog);
		}


		@Override
		protected VBox initializeDialog() {
			// TODO Auto-generated method stub
			return null;
		}
	 

}
