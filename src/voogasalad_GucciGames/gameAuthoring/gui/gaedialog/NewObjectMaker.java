package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewObjectMaker extends GaeDialog{	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private GroovyTabPane groovyTabPane;
	private VBox myContent = new VBox();
	private Stage unitMakerDialog = new Stage();
	private Map<Integer, String> groovyBuffer = new HashMap<Integer, String>();
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
		groovyTabPane = new GroovyTabPane(prop, saveGroovy);
		save = new SaveField(dialogElements, controller);
		MakerDialog dialog = new MakerDialog(dialogElements, customContent, groovyTabPane, save);	
		customContent.setDialogElements(dialogElements);
		myContent = dialog.getContent();
		Scene tileDialogScene = new Scene(myContent, WIDTH, HEIGHT);
		tileDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		unitMakerDialog.setScene(tileDialogScene);		
		 
	 }	

	 
	 protected int getIdForTab(Tab t){
		 return Integer.parseInt(t.getText().split("\\s+")[1]);
	 }
	 	 

	 protected Button createAddButton(Properties prop, String btnKey, String headerKey, GroovyTabPane groovyTabPane){
		 Button addBtn = new Button(prop.getProperty(btnKey));
		 addBtn.setOnAction(e -> {
			 groovyTabPane.addGroovyTab();			 
		 });
		 return addBtn;		 
	 }
	 
	 protected ISaveObjProperty setSavePropertyFunction(ObjectProperty property, ISaveObjProperty saveObjProperty){
			saveObjProperty = (propName, prop) -> {
				try {
					property.addPropertyElement(propName, prop);
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			return saveObjProperty;
	 }
	 
	 protected ISaveGroovy setSaveGroovyFunctions(Map<Integer, String> buffer, ISaveGroovy saveGroovy){		 
			saveGroovy = (str, index) -> {
				buffer.put(index, str);
				//debug
				buffer.forEach((k, v) -> System.out.println(" " + k + " " + v));
				System.out.println("---------");
			};	
			return saveGroovy;
		 
	 }
	 
	 protected ISaveCustomObj setSaveCustomObj(ISaveCustomObj saveCustomObject){
		 saveCustomObject = p -> {System.out.println("here");};
		 return saveCustomObject;
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
