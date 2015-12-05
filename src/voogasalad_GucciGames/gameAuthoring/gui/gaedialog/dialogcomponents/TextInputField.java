package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TextInputField extends DialogComponent{
	
	private DialogElements dialogElements;
	private String propKey;
	private TextField textField;
	private Properties prop;
	
	public TextInputField(Properties prop, String propKey){
		this.prop = prop;
		this.propKey = propKey;
		makeTextInputField();
		
	}
	
	private void makeTextInputField(){
		Text title = new Text(prop.getProperty(propKey));		
		textField = new TextField();	
		this.add(title, 0, 0);
		this.add(textField, 1, 0);
	}
	
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
