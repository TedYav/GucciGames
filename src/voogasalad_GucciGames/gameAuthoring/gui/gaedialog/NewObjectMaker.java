package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;
import voogasalad_GucciGames.gameAuthoring.properties.MapObjectProperty;
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

public abstract class NewObjectMaker extends GaeDialog{	
	public NewObjectMaker(){
		 super();		 
	 }	 
	 protected VBox initDefaultContentForObjMaker(Properties prop, VBox customContent,
			 GroovyTabPane groovyTabPane, String styleId, ISaveObjProperty saveObjProperty, 
			 ISaveCustomObj saveCustomObject, MapObjectProperty property){
		 
		 VBox content = new VBox();	 
		 Text titleTextElement = new Text(prop.getProperty("title"));
		 titleTextElement.setId("title");
		 TextField nameTextField = new TextField();	
		 nameTextField.textProperty().addListener((observable, oldValue, newValue)->{
			 
			 saveObjProperty.saveObjProperty("name", newValue);
		 });

		 HBox nameElement = createElement(prop.getProperty("name"), 
				 nameTextField, "hbox-element");
		 HBox imageElement = createElement(prop.getProperty("image"),
				 makeBrowseElement(prop, "browse", "filechoosertitle", saveObjProperty), "hbox-element");		 
		 VBox tabPaneElements = groovyTabPane.getContent();		 
		 Button saveCustomObjBtn = new Button(prop.getProperty("savebtn"));
		 
		 if(saveCustomObject == null){
			 System.out.println("save Custom Obj null");
		 }
		 
		 saveCustomObjBtn.setOnAction(e -> {
			 ISaveCustomObj saveCustomObj = null;
			 ISaveCustomObj saveCustomO = this.setSaveCustomObj(saveCustomObj);
			 saveCustomO.saveCustomObj(property);});
		 
		 content.getChildren().addAll(titleTextElement, nameElement, imageElement, 
				 customContent, tabPaneElements, saveCustomObjBtn);
		 content.setId(styleId);
		 return content;
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
	 
	 protected ISaveObjProperty setSavePropertyFunction(MapObjectProperty property, ISaveObjProperty saveObjProperty){
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
	 
	 
	 protected abstract VBox initializeCustomContent();
	 

}
