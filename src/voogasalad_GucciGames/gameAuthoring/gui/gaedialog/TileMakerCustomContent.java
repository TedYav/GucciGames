package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.Properties;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TileMakerCustomContent extends DialogContent{
	private VBox content = new VBox();
	private DialogElements dialogElements;
	
	public TileMakerCustomContent(DialogElements dialogElements){		
		this.dialogElements = dialogElements;
	}
	
	protected VBox initializeCustomContent(){
		VBox vbox = new VBox();
		Properties prop = dialogElements.getDialogProperties();
		ISaveObjProperty saveObjProperty = dialogElements.getSaveObjProperty();		
		HBox prop1 = createElement(prop.getProperty("prop1"),
				makeRadioButtons(prop, "prop1", "prop1_items", saveObjProperty),
				"hbox-element");
		HBox prop2 = createElement(prop.getProperty("prop2"),
				makeRadioButtons(prop, "prop2", "prop2_items", saveObjProperty), "hbox-element");	
		vbox.getChildren().addAll(prop1, prop2);
		vbox.setId("vbox-element");
		return vbox;
	}
	
	protected VBox getContent(){
		return content;
	}
	
	public void loadCustomContent(Properties contentProp){
		
	}

}
