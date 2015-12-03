package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public abstract class DialogComponent extends GridPane {
	
//	protected HBox createElement(String name, Node n, String hboxId){
//		HBox hbox = new HBox();
//		Text key = new Text(name);
//		hbox.getChildren().addAll(key, n);	
//		hbox.setId(hboxId);
//		return hbox;	
//	} 
	public DialogComponent(){
		this.setHgap(10);
		this.setVgap(10);
		//this.setPadding(new Insets(20, 150, 10, 10));
	}
	
	protected List<String> parseStringToList(Properties prop, String itemsKey){
		String items = prop.getProperty(itemsKey);	
		List<String> propertiesList = Arrays.asList(items.split("\\s*,\\s*"));	
		return propertiesList;		
	}
	
	public abstract void setSelected(String s);
	
	public abstract String getSelected();
	

}
