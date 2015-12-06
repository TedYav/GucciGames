package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;


public abstract class AGaeDialog extends javafx.scene.control.Dialog {
	protected final ButtonType mySave;
	
	public AGaeDialog(){
		mySave = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
	}

	protected abstract void setSaveAction();
}