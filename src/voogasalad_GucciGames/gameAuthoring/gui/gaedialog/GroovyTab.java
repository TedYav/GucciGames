package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroovyTab {
	
	private Tab tab = new Tab();
	private VBox content = new VBox();
	private DialogElements dialogElements;
	private TextInputField name;
	private TextArea groovyTextArea;

	public GroovyTab(DialogElements dialogElements){
		this.dialogElements = dialogElements;
		initTab();
	}
	
	private void initTab(){
		Text title = new Text(dialogElements.getDialogProperties().getProperty("groovytitle"));
		name = new TextInputField(dialogElements, "attributename");
		groovyTextArea = new TextArea();
		content.getChildren().addAll(title, name.getContent(), groovyTextArea);
		tab.setContent(content);
	}
	
	protected void setValuesForTab(String nameVal, String groovyContent){
		name.setSelected(nameVal);
		groovyTextArea.setText(groovyContent);
	}
	protected Tab getTab(){
		return tab;
	}
}
