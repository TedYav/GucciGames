package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.util.Properties;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class NewObjectMaker extends GaeDialog{
	
	public NewObjectMaker(){
		 super();	
		 
	 }
	 
	 protected VBox initializeDefaultContent(Properties prop, GroovyTabPane groovyTabPane){
		 VBox content = new VBox();
		 content.setId("vbox-content");
		 Text titleTextElement = new Text(prop.getProperty("title"));
		 titleTextElement.setId("title");
		 TextField nameTextField = new TextField();		
		 HBox nameElement = createElement(prop.getProperty("name"), 
				 nameTextField, "field-title-element");
		 HBox imageElement = createElement(prop.getProperty("image"),
				 makeBrowseElement(prop, "browse", "filechoosertitle"), "" );
		 VBox tabPaneElements = groovyTabPane.getContent();
		 content.getChildren().addAll(titleTextElement, nameElement, imageElement, tabPaneElements);
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
}
