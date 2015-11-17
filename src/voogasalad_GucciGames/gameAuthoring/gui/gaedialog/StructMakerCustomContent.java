package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import javafx.scene.layout.VBox;

public class StructMakerCustomContent extends DialogContent{
	
	private VBox content = new VBox();
	private DialogElements dialogElements;
	
	public StructMakerCustomContent(){
		
	}
	
	protected void setDialogElements(DialogElements dialogElements){
		this.dialogElements = dialogElements;
	}
	
	protected VBox initializeCustomContent() {
		// TODO Auto-generated method stub
		VBox vbox = new VBox();
		RadioBtnField prop1 = new RadioBtnField(dialogElements, "prop1", "prop1_items");
		RadioBtnField prop2 = new RadioBtnField(dialogElements, "prop1", "prop1_items");
		vbox.getChildren().addAll(prop1.getContent(), prop2.getContent());
		vbox.setId("vbox-element");
		return vbox;
	}
	
	protected VBox getContent(){
		return content;
	}

}
