package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.GaeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;

public class SettingsDialog extends GaeDialog  implements ISwitchSettingsPane{
	
	private ArrayList<VBox> myContents;
	
	private Pane myPane = new Pane();
	
	private int currentIndex = 0;
	
	
	
	public SettingsDialog(){
		
	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void switchSettingsPane(int currentIndex) {
		// TODO Auto-generated method stub
		if (currentIndex + 1 < myContents.size()){
			myPane.getChildren().removeAll(myPane.getChildren());
			myPane.getChildren().add(myContents.get(currentIndex + 1)); 
		}
		
	}

}
