package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.List;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DropDownMenuField  extends DialogComponent{
	
	private DialogElements dialogElements;
	private String propKey;
	private String itemsKey;
	private HBox content = new HBox();
	private ComboBox dropDown = new ComboBox();
	
	public DropDownMenuField(DialogElements dialogElements, String propKey, String itemsKey){
		this.dialogElements = dialogElements;
		this.propKey = propKey;
		this.itemsKey = itemsKey;
		this.makeDropDownList();
		
	}
	
	protected void makeDropDownList(){
		Text label = new Text(propKey);
		List<String> propertiesList = parseStringToList(dialogElements.getDialogProperties(), itemsKey);
		ObservableList<String> options = FXCollections.observableArrayList(propertiesList);
		dropDown.setItems(options);	
		addListenerToDropDown();
		content.getChildren().addAll(label, dropDown);
		content.setId("hbox-element");
	}
	
	private void addListenerToDropDown(){
		dropDown.setOnAction(e -> {
			String s = dropDown.getSelectionModel().getSelectedItem().toString();
		});		
	}

	@Override
	HBox getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	void setSelected(String s) {
		// TODO Auto-generated method stub
		dropDown.getSelectionModel().select(s);
	}
	
	protected String getSelected(){
		if(dropDown.getSelectionModel().getSelectedItem() == null){
			return "";
		}
		return dropDown.getSelectionModel().getSelectedItem().toString();
	}

}
