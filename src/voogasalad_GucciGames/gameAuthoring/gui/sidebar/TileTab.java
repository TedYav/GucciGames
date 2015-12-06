package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.NewObMakerDialog;


public class TileTab extends ATab {

	
	public TileTab(SideBar bar){
		super(bar);

		setText("Tiles");
		
		init(myController.getImmutableTileTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObMakerDialog addNewTileDialog = new NewObMakerDialog(myController);
		addNewTileDialog.showAndWait();
		
	}

}
