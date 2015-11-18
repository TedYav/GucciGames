package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TextInputField extends DialogComponent{
	
	private DialogElements dialogElements;
	private String propKey;
	private HBox content = new HBox();
	private TextField textField;
	
	public TextInputField(DialogElements dialogElements, String propKey){
		this.dialogElements = dialogElements;
		this.propKey = propKey;
		makeTextInputField();
		
	}
	
	private void makeTextInputField(){
		Text title = new Text(dialogElements.getDialogProperties().getProperty(propKey));		
		textField = new TextField();	
		addListenerToTextInputField();
		content.getChildren().addAll(title,textField);
		content.setId("hbox-element");
	
	}
	
	private void addListenerToTextInputField(){
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			dialogElements.getSaveObjProperty().
			saveObjProperty(dialogElements.getDialogProperties().getProperty(propKey), newValue);		
		});
		
	}

	
	protected TextField getTextField(){
		return textField;
	}
	@Override
	HBox getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public void setSelected(String s) {
		// TODO Auto-generated method stub
		textField.setText(s);
		
	}

}
