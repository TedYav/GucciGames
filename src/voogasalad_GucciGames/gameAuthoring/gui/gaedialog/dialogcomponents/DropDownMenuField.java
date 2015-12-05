package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class DropDownMenuField  extends DialogComponent{
	
	private Properties prop;
	private String propKey;
	private String itemsKey;
	private Text label;
	private List<String> propertiesList = new ArrayList<String>();
	private ComboBox<String> dropDown = new ComboBox<String>();
	
	public DropDownMenuField(Properties prop, String propKey, 
			String itemsKey){
		this.prop = prop;
		this.propKey = propKey;
		this.itemsKey = itemsKey;
		label = new Text(propKey);
		propertiesList = parseStringToList(prop, itemsKey);
		this.makeDropDownList();
		
	}
	
	public DropDownMenuField(List<String> items){
		this.propertiesList = items;
		makeDropDownList();		
	}
	

	
	protected void makeDropDownList(){	
		ObservableList<String> options = FXCollections.observableArrayList(propertiesList);
		dropDown.setItems(options);	
		this.add(label, 0, 0);
		this.add(dropDown, 1 , 0);
		
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
