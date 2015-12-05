package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import javafx.scene.text.Text;

public class DropDownMenuField  extends DialogComponent{
	
	private DialogElements dialogElements;
	private String propKey;
	private String itemsKey;
	private Text label;
	private List<String> propertiesList = new ArrayList<String>();
	private ComboBox<String> dropDown = new ComboBox<String>();
	
	public DropDownMenuField(DialogElements dialogElements, String propKey, 
			String itemsKey){
		this.dialogElements = dialogElements;
		this.propKey = propKey;
		this.itemsKey = itemsKey;
		label = new Text(propKey);
		propertiesList = parseStringToList(dialogElements.getDialogProperties(), itemsKey);
		this.makeDropDownList();
		
	}
	
	public DropDownMenuField(List<String> items){
		this.propertiesList = items;
		makeDropDownList();
		
	}
	
	
	
	protected void makeDropDownList(){
		
		
		ObservableList<String> options = FXCollections.observableArrayList(propertiesList);
		dropDown.setItems(options);	
		addListenerToDropDown();
		this.getChildren().addAll(label, dropDown);
		
	}
	
	private void addListenerToDropDown(){
		dropDown.setOnAction(e -> {
			String s = dropDown.getSelectionModel().getSelectedItem().toString();
			//listViewInterface.addListItem(s);
		});		
	}

	@Override
	public void setSelected(String s) {
		dropDown.getSelectionModel().select(s);
	}
	
	public String getSelected(){
		if(dropDown.getSelectionModel().getSelectedItem() == null){
			return "";
		}
		return dropDown.getSelectionModel().getSelectedItem().toString();
	}

}
