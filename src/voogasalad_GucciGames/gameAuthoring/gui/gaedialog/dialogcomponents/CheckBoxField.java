package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import javafx.scene.control.CheckBox;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;


public class CheckBoxField extends DialogComponent{
	private DialogElements dialogElements;
	private String propKey;
	private CheckBox checkBox;
	
	public CheckBoxField(DialogElements dialogElements, String propKey){
		this.dialogElements = dialogElements;
		this.propKey = propKey;
		makeCheckBoxes();
	}
	
	protected void makeCheckBoxes(){
		checkBox = new CheckBox();	
		checkBox.setText(dialogElements.getDialogProperties().getProperty(propKey));
		this.getChildren().addAll(checkBox);
		this.setId("hbox-element");
	}
	
	public CheckBox getCheckBox(){
		return checkBox;
	}
	
	public String getPropKey(){
		return propKey;
	}

	@Override
	public void setSelected(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSelected() {
		// TODO Auto-generated method stub
		return null;
	}

}
