package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.NewObjectMaker;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveCustomObj;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.TileMakerCustomContent;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;


public class TileTab extends AbstractTab {

	private ISaveCustomObj saveCustomObj;
	
	public TileTab(IGuiGaeController controller, ISaveCustomObj saveCustomObj){
		super(controller);
		this.saveCustomObj = saveCustomObj;
		setText("Tiles");

		for(MapObjectType maptype : myController.getImmutableTileTypes()){
			allImagePaths.add(maptype.getMyImagePath());
		}

		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/land.png", "voogasalad_GucciGames/graphics/water.png", "voogasalad_GucciGames/graphics/hurricane.png","voogasalad_GucciGames/graphics/fire.png","voogasalad_GucciGames/graphics/lava.png","voogasalad_GucciGames/graphics/mountain.png","voogasalad_GucciGames/graphics/sand.png");

		addImages();
		addImageHandler();
		addDragDropListener(myController.getImmutableTileTypes());
		addAddButtonListener();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	//TODO: create add button, pass saveCustomObj to dialog

	@Override
	protected void addAddButtonListener() {
		// TODO Auto-generated method stub
		myAddButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				//dialog
				NewObjectMaker addNewTileDialog = new NewObjectMaker(new TileMakerCustomContent(), myController);
				addNewTileDialog.showDialog();
			}
		});
	}

}


