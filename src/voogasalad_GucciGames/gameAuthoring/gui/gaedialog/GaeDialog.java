package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

abstract public class GaeDialog{	
	public GaeDialog(){
		
	}
		
	protected Properties loadProperties(String path){
		Properties prop = new Properties();
		InputStream input = null;
		input = getClass().getResourceAsStream(path);
		try {
			prop.load(input);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;		
	}
	
	protected abstract VBox initializeDialog();


	public void showDialog(Stage stage){
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	
	 protected ISaveGroovy setSaveGroovyFunctions(Map<String, String> groovyBuffer2, ISaveGroovy saveGroovy){		 
			saveGroovy = (str, strName) -> {
				groovyBuffer2.put(strName, str);
				//debug
				groovyBuffer2.forEach((k, v) -> System.out.println(" " + k + " " + v));
				System.out.println("---------");
			};	
			return saveGroovy;
		 
	 }
	 
//	 protected ISaveObjProperty setSavePropertyFunction(ObjectProperty property, ISaveObjProperty saveObjProperty){
//			saveObjProperty = (propName, prop) -> {
//				try {
//					property.addPropertyElement(propName, prop);
//				} catch (InvalidInputException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			};
//			return saveObjProperty;
//	 }
	 
	

}
