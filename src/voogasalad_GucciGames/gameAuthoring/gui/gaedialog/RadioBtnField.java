package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class RadioBtnField extends DialogComponent {
	
	private DialogElements dialogElements;
	private String propKey;
	private String itemsKey;
	private ToggleGroup group = new ToggleGroup();
	private HBox content = new HBox();
	
	
	public RadioBtnField(DialogElements dialogElements, String propKey, String itemsKey){
		this.dialogElements = dialogElements;
		this.propKey = propKey;
		this.itemsKey = itemsKey;
		makeRadioButtons();
	}
		
	protected void makeRadioButtons(){
		Text label = new Text(dialogElements.getDialogProperties().getProperty(propKey));
		List<RadioButton> checkBoxList = new ArrayList<RadioButton>();
		List<String> propertiesList = parseStringToList(dialogElements.getDialogProperties(), itemsKey);
		for (int i = 0; i < propertiesList.size(); i++){
			checkBoxList.add(new RadioButton(propertiesList.get(i)));
		}
		checkBoxList.forEach(radioBtn->{
			radioBtn.setToggleGroup(group);
			radioBtn.setUserData(radioBtn.getText());});		
		addListenerToToggleGroup();
		content.getChildren().add(label);
		content.getChildren().addAll( checkBoxList);
	}
	
	private void addListenerToToggleGroup() {
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				if(group.getSelectedToggle() != null){
					dialogElements.getSaveObjProperty()
					.saveObjProperty(propKey, group.getSelectedToggle().getUserData().toString());
					
				}			
			}
		});
	}
	
	@Override
	protected HBox getContent(){
		return content;	
	}
	
	@Override
	public void setSelected(String value){
		for(Toggle t: group.getToggles()){
			if(t.getUserData().toString() == value){
				group.selectToggle(t);
			}
		}
	}


}
