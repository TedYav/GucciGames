package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

abstract class DialogContent {
	protected HBox createElement(String name, Node n, String hboxId){
		HBox hbox = new HBox();
		Text key = new Text(name);
		hbox.getChildren().addAll(key, n);	
		hbox.setId(hboxId);
		return hbox;	
	}
	
	protected List<String> parseStringToList(Properties prop, String itemsKey){
		String items = prop.getProperty(itemsKey);	
		List<String> propertiesList = Arrays.asList(items.split("\\s*,\\s*"));	
		return propertiesList;		
	}
	
	protected abstract VBox getContent();
	
	protected abstract void setDialogElements(DialogElements dialogElements);
	

}
