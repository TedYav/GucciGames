package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.stylesheets.IListView;
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
	private Text label;
	private List<String> propertiesList = new ArrayList<String>();
	private ComboBox<String> dropDown = new ComboBox<String>();
	private IListView listViewInterface;
	
	public DropDownMenuField(DialogElements dialogElements, String propKey, 
			String itemsKey, IListView listViewInterface){
		this.listViewInterface = listViewInterface;
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
			listViewInterface.addToListView(s);
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
