package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import javafx.scene.control.ButtonType;


public abstract class AGaeDialog extends javafx.scene.control.Dialog {
	
	public AGaeDialog(){
		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
	}

}
