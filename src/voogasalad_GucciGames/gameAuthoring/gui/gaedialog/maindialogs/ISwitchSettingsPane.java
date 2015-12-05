package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

public interface ISwitchSettingsPane {
	
	public void switchSettingsPane(Node n);
		
	public Optional<ButtonType> getDialogButtonResponse();
	


}
