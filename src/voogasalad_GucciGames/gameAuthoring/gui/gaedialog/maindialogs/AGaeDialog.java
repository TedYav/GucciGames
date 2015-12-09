package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;


public abstract class AGaeDialog<T> extends javafx.scene.control.Dialog<T> {
	protected final ButtonType mySave;
	
	public AGaeDialog(){
		mySave = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
	}
	
	public void showAlert(String title, String contentText){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	protected abstract void setSaveAction();
}
