package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.TileMaker;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;


public class TileTab extends AbstractTab {

	public TileTab(IGuiGaeController controller){
		super(controller);
		setText("Tiles");
//		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/land.png", "voogasalad_GucciGames/graphics/water.png", "voogasalad_GucciGames/graphics/hurricane.png","voogasalad_GucciGames/graphics/fire.png","voogasalad_GucciGames/graphics/lava.png","voogasalad_GucciGames/graphics/mountain.png","voogasalad_GucciGames/graphics/sand.png");
		for(MapObjectType maptype : myController.getImmutableTileTypes()){
			allImagePaths.add(maptype.getMyImagePath());
		}
		addImages();
		addImageHandler();
		addDragDropListener();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addDragDropListener(){
		List<MapObjectType> currMapTypeList = myController.getImmutableTileTypes();
		for(int i=0; i<allImageViews.size(); i++){
			ImageView imageview = allImageViews.get(i);
			imageview.setOnDragDetected(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					/* drag was detected, start a drag-and-drop gesture*/
					/* allow any transfer mode */
					Dragboard db = imageview.startDragAndDrop(TransferMode.ANY);

					/* Put a image on a dragboard */
					ClipboardContent content = new ClipboardContent();
					content.putImage(imageview.getImage());

					int imageviewId = allImageViews.indexOf(imageview);
					MapObjectType currMapType = currMapTypeList.get(imageviewId);
					myController.setMapObjectTypeToMap(currMapType);

					db.setContent(content);
					event.consume();
				}
			});

		}
	}

	@Override
	protected void addAddButtonListener() {
		// TODO Auto-generated method stub
		myAddButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				//dialog
				TileMaker addNewTileDialog = new TileMaker();
			}
		});
	}

	@Override
	protected void addPlayerOwnership() {
		// TODO Auto-generated method stub
		
	}
}


