package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroovyTab {
	
	private Tab tab = new Tab();
	private VBox content = new VBox();
	private DialogElements dialogElements;
	private TextArea groovyTextArea;
	private String tabName; 

	public GroovyTab(DialogElements dialogElements, String name){
		
		this.dialogElements = dialogElements;
		this.tabName = name;
		initTab();
	}
	
	private void initTab(){
		tab.setText(tabName);
		Text title = new Text(dialogElements.getDialogProperties().getProperty("groovytitle"));
		title.setId("groovy-title");
		groovyTextArea = new TextArea();
		content.getChildren().addAll(title, groovyTextArea);
		content.setId("groovy-tab-content");
		tab.setContent(content);
	}
	
	protected void setValuesForTab(String nameVal, String groovyContent){
		tab.setText(nameVal);
		groovyTextArea.setText(groovyContent);
	}
	
	protected TextArea getTextArea(){
		return groovyTextArea;
	}

	protected Tab getTab(){
		return tab;
	}
	
	protected void setSelected(){
		content.setId("groovy-tab-selected-content");
	}
	
	protected void setName(String s){
		tabName = s;
	}
	protected String getName(){
		return tabName;
	}

}
