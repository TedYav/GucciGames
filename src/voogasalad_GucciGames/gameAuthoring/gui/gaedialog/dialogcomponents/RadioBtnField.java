package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class RadioBtnField extends DialogComponent{ 
	
	private DialogElements dialogElements;
	private String propKey;
	private String itemsKey;
	private ToggleGroup group = new ToggleGroup();
	private List<String> itemsList = new ArrayList<String>();
	private Text label = new Text();

	
	
	public RadioBtnField(DialogElements dialogElements, String propKey, String itemsKey){
		this.dialogElements = dialogElements;
		this.propKey = propKey;
		this.itemsKey = itemsKey;
		createItemList();
		makeRadioButtons();
	}
	
	public RadioBtnField(List<String> items){
		itemsList = items;
		makeRadioButtons();
		
	}
	
	private void createItemList(){
		label = new Text(dialogElements.getDialogProperties().getProperty(propKey));
		itemsList = parseStringToList(dialogElements.getDialogProperties(), itemsKey);
	}
		
	protected void makeRadioButtons(){
		List<RadioButton> checkBoxList = new ArrayList<RadioButton>();

		for (int i = 0; i < itemsList.size(); i++){
			checkBoxList.add(new RadioButton(itemsList.get(i)));
		}
		this.add(label, 0, 0);
		int col = 0;
		for(RadioButton radioBtn: checkBoxList){
			radioBtn.setToggleGroup(group);
			radioBtn.setUserData(radioBtn.getText());
			//this.add(radioBtn, col, 1);
			this.add(radioBtn, 1, col);
			col++;
		}		
	
	}

	
	@Override
	public void setSelected(String value){
		for(Toggle t: group.getToggles()){
			if(t.getUserData().toString() == value){
				group.selectToggle(t);
			}
		}
	}
	
	public String getSelected(){
		return group.getSelectedToggle().getUserData().toString();
	}


}
