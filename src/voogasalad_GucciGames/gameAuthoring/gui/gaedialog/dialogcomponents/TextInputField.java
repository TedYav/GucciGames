package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TextInputField extends DialogComponent{
	
	private DialogElements dialogElements;
	private String propKey;
	private TextField textField;
	
	public TextInputField(DialogElements dialogElements, String propKey){
		this.dialogElements = dialogElements;
		this.propKey = propKey;
		makeTextInputField();
		
	}
	
	private void makeTextInputField(){
		Text title = new Text(dialogElements.getDialogProperties().getProperty(propKey));		
		textField = new TextField();	
		//addListenerToTextInputField();
		this.getChildren().addAll(title,textField);
		this.setId("hbox-element");
	
	}
	
//	private void addListenerToTextInputField(){
//		textField.textProperty().addListener((observable, oldValue, newValue) -> {
//			//dialogElements.getSaveObjProperty().
//			//saveObjProperty(dialogElements.getDialogProperties().getProperty(propKey), newValue);		
//		});
//		
//	}
	
	public String getTextInput(){
		return getTextField().getText();
	}

	
	protected TextField getTextField(){
		return textField;
	}

	@Override
	public void setSelected(String s) {
		// TODO Auto-generated method stub
		textField.setText(s);
		
	}

	@Override
	public String getSelected() {
		// TODO Auto-generated method stub
		return null;
	}

}
