package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.NewObjectMaker;


public class TileTab extends ATab {

	
	public TileTab(SideBar bar){
		super(bar);

		setText("Tiles");
		
		init(myController.getImmutableTileTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObjectMaker addNewTileDialog = new NewObjectMaker(myController);
		addNewTileDialog.showAndWait();
		
	}

}
