package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.Properties;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class NewObjectMaker extends GaeDialog{
	 public NewObjectMaker(){
		 super();
		 
	 }
	 
	 
	 protected void makeGroovyTab(Properties prop, String headerKey, TabPane tabPane){
		 Tab tab = new Tab();
		 VBox content = new VBox();
		 Text title = new Text("Define Attribute Using Groovy");
		 TextArea textArea = new TextArea();
		 content.getChildren().addAll(title, textArea);
		 tabPane.getTabs().add(tab);
		 
	 }
	 
	 
	 
	 protected void removeTab(int index){
		 
		 
	 }
	 
	


}
