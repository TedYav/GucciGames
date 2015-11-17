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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class DialogContent {
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
	
	protected ComboBox makeDropDownList(Properties prop, String itemsKey){
		List<String> propertiesList = parseStringToList(prop, itemsKey);
		ComboBox dropDown = new ComboBox();
		ObservableList<String> options = FXCollections.observableArrayList(propertiesList);
		dropDown.setItems(options);
		return dropDown;	
	}
	/*
	protected HBox makeRadioButtons(Properties prop, String name, String itemsKey, ISaveObjProperty saveObjProperty){
		HBox checkBoxes = new HBox();
		final ToggleGroup group = new ToggleGroup();
		List<RadioButton> checkBoxList = new ArrayList<RadioButton>();
		List<String> propertiesList = parseStringToList(prop, itemsKey);
		for (int i = 0; i < propertiesList.size(); i++){
			checkBoxList.add(new RadioButton(propertiesList.get(i)));
		}
		checkBoxList.forEach(radioBtn->{
			radioBtn.setToggleGroup(group);
			radioBtn.setUserData(radioBtn.getText());});
		addListenerToToggleGroup(group, saveObjProperty, name);
		checkBoxes.getChildren().addAll(checkBoxList);
		return checkBoxes;
	}
	
	
	
	private void addListenerToToggleGroup(ToggleGroup group, ISaveObjProperty saveObjProperty, String propKey ){
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				if(group.getSelectedToggle() != null){
					saveObjProperty.saveObjProperty(propKey, group.getSelectedToggle().getUserData().toString());
					
				}
				
			}
		});
	}
*/
	protected ScrollBar makeScrollBar(Properties prop, String minKey, String maxKey, String incrementKey){
		ScrollBar scrollBar = new ScrollBar();
		scrollBar.setMin(Double.parseDouble(prop.getProperty(minKey)));
		scrollBar.setMax(Double.parseDouble(prop.getProperty(maxKey)));
		scrollBar.setUnitIncrement(Double.parseDouble(prop.getProperty(incrementKey)));
		return scrollBar;
	}
	
	protected HBox makeBrowseElement(Properties prop, String browseKey, String fileChooserKey, ISaveObjProperty saveObjProperty){
		HBox hbox = new HBox();
		TextField pathTextField = new TextField();
		Button browseBtn = new Button(prop.getProperty(browseKey));
		browseBtn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(prop.getProperty(fileChooserKey));
			fileChooser.getExtensionFilters().add(
			         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));	         
			File selectedFile  = fileChooser.showOpenDialog(null);
			//TODO: get file path
			pathTextField.setText(selectedFile.getAbsolutePath());
		});
		
		pathTextField.textProperty().addListener((observable, oldValue, newValue)->{
			 saveObjProperty.saveObjProperty("imagepath", newValue);
		 });

		hbox.getChildren().addAll(pathTextField, browseBtn);	
		return hbox;
	}
}
