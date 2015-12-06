package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.NewObjMakerDialog;


public class TileTab extends ATab {
	private static final String TYPE = "tile";
	
	public TileTab(SideBar bar){
		super(bar);

		setText("Tiles");
		
		init(myController.getImmutableTileTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObjMakerDialog addNewTileDialog = new NewObjMakerDialog(myController, TYPE);
		addNewTileDialog.showAndWait();
		
	}

}
