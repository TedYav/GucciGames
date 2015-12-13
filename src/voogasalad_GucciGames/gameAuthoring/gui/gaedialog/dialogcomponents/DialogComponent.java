package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public abstract class DialogComponent extends GridPane {

	public DialogComponent(){
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));
	}
	
	protected List<String> parseStringToList(Properties prop, String itemsKey){
		String items = prop.getProperty(itemsKey);	
		List<String> propertiesList = Arrays.asList(items.split("\\s*,\\s*"));	
		return propertiesList;		
	}
	
	public abstract void setSelected(String s);
	
	public abstract String getSelected();
	

}
