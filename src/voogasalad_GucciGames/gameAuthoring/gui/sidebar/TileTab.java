package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.NewObjectMaker;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveCustomObj;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.TileMakerCustomContent;


public class TileTab extends ATab {

	private ISaveCustomObj saveCustomObj;
	
	public TileTab(SideBar bar, ISaveCustomObj saveCustomObj){
		super(bar);
		this.saveCustomObj = saveCustomObj;
		setText("Tiles");
		init(myController.getImmutableTileTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObjectMaker addNewTileDialog = new NewObjectMaker(new TileMakerCustomContent(), myController);
		addNewTileDialog.showDialog();
		
	}

}
