package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.NewObjectMaker;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.TileMakerCustomContent;



public class TileTab extends ATab {

	
	public TileTab(SideBar bar){
		super(bar);

		setText("Tiles");
		
		init(myController.getImmutableTileTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObjectMaker addNewTileDialog = new NewObjectMaker(new TileMakerCustomContent(), myController);
		addNewTileDialog.showDialog();
		
	}

}
