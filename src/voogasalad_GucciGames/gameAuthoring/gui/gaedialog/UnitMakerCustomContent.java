package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UnitMakerCustomContent extends DialogContent{
	
	private VBox content = new VBox();
	private DialogElements dialogElements;
	
	public UnitMakerCustomContent(){
		//initializeCustomContent();
	}
	
	protected void setDialogElements(DialogElements dialogElements){
		this.dialogElements = dialogElements;
		dialogElements.getDialogProperties().setProperty("type", "unit");

	}
	
	protected void initializeCustomContent() {
		// TODO Auto-generated method stub
		RadioBtnField prop1 = new RadioBtnField(dialogElements, "prop1", "prop1_items");
		RadioBtnField prop2 = new RadioBtnField(dialogElements, "prop1", "prop1_items");
		content.getChildren().addAll(prop1.getContent(), prop2.getContent());
		content.setId("vbox-element");

	}
	
	protected VBox getContent(){
		return content;
	}

}
