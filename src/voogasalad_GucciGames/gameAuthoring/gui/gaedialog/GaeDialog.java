package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

abstract public class GaeDialog{
	
	public GaeDialog(){
		//initializeDialog();
		
		
	}
	protected abstract VBox initializeDialog();
	
	protected HBox initializeControl(Properties prop, String keyStyleId){
		HBox controls = new HBox();
		controls.setId("hbox-control");
		Button cancelBtn = new Button( prop.getProperty("cancel"));
		Button okBtn = new Button(prop.getProperty("ok"));
		controls.getChildren().addAll(cancelBtn, okBtn);	
		return controls;
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
	
	protected HBox createElement(String name, Node n, String keyStyleId){
		HBox hbox = new HBox();
		Text key = new Text(name);
		key.setId(keyStyleId);
		hbox.getChildren().addAll(key, n);
		
		return hbox;	
	}
	
	public void showGameSettingsDialog(Stage dialogStage, Stage ownerStage){
		dialogStage.initModality(Modality.APPLICATION_MODAL);
		dialogStage.initOwner(ownerStage);
		dialogStage.show();
	}
	
	
	protected List<String> parseStringToList(Properties prop, String itemsKey){
		String items = prop.getProperty(itemsKey);	
		List<String> propertiesList = Arrays.asList(items.split("\\s*,\\s*"));	
		return propertiesList;		
	}
	
	protected ComboBox makeDropDownList(Properties prop, String itemsKey){
		List<String> propertiesList = parseStringToList(prop, itemsKey);
		ComboBox dropDown = new ComboBox();
		ObservableList<String> options = FXCollections.observableArrayList(propertiesList);
		dropDown.setItems(options);
		return dropDown;	
	}
	protected HBox makeRadioButtons(Properties prop, String itemsKey){
		HBox checkBoxes = new HBox();
		final ToggleGroup group = new ToggleGroup();
		List<RadioButton> checkBoxList = new ArrayList<RadioButton>();
		List<String> propertiesList = parseStringToList(prop, itemsKey);
		for (int i = 0; i < propertiesList.size(); i++){
			
			checkBoxList.add(new RadioButton(propertiesList.get(i)));
		}
		checkBoxList.forEach(radioBtn->radioBtn.setToggleGroup(group));
		checkBoxes.getChildren().addAll(checkBoxList);
		return checkBoxes;
	}
	
	protected ScrollBar makeScrollBar(Properties prop, String minKey, String maxKey, String incrementKey){
		ScrollBar scrollBar = new ScrollBar();
		scrollBar.setMin(Double.parseDouble(prop.getProperty(minKey)));
		scrollBar.setMax(Double.parseDouble(prop.getProperty(maxKey)));
		scrollBar.setUnitIncrement(Double.parseDouble(prop.getProperty(incrementKey)));
		return scrollBar;
	}
	
	protected HBox makeBrowseElement(Properties prop, String browseKey, String fileChooserKey){
		HBox hbox = new HBox();
		TextField pathTextField = new TextField();
		Button browseBtn = new Button(prop.getProperty(browseKey));
		browseBtn.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(prop.getProperty(fileChooserKey));
			fileChooser.showOpenDialog(null);
		});
		hbox.getChildren().addAll(pathTextField, browseBtn);	
		return hbox;
	}
	



	
	
	
	

}
