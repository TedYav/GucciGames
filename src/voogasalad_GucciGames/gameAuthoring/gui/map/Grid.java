package voogasalad_GucciGames.gameAuthoring.gui.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.map.cell.Cell;
import voogasalad_GucciGames.gameAuthoring.gui.map.cell.ICell;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

class Grid extends Pane implements ICellGrid{

	private final ImageView myBackground;
	private final DoubleProperty myCellSize;
	private final Set<ICell> selectedCells = new HashSet<>();
	private final Map<GridPoint, ICell> myCells = new HashMap<>();
	private final AGuiGaeController myController;
	private final int myLevelID;

	public Grid(DoubleProperty cellSize, AGuiGaeController controller, int id) {
		myLevelID = id;
		myController = controller;
		myBackground = new ImageView();
		getChildren().setAll(myBackground);
		myBackground.fitWidthProperty().bind(widthProperty());
		myBackground.fitHeightProperty().bind(heightProperty());
		myCellSize = cellSize;
		new GridMouseTracker(this);
		new GridSelector(this);

		//myMapObjects = controller.getMapObjects();
	}

	public void initGrid(int width, int height) {
		maxWidthProperty().bind(myCellSize.multiply(width));
		maxHeightProperty().bind(myCellSize.multiply(height));
		minWidthProperty().bind(myCellSize.multiply(width));
		minHeightProperty().bind(myCellSize.multiply(height));
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Cell cell = new Cell(this, x, y);
				myCells.put(new GridPoint(x, y), cell);
				getChildren().add(cell);
			}
		}
	}

	public void setBackground(Image img) {
		myBackground.setImage(img);
	}

	public DoubleProperty getCellSize() {
		return myCellSize;
	}

	public boolean selectCell(ICell cell) {
		return selectedCells.add(cell);
	}

	public boolean deselectCell(ICell cell) {
		return selectedCells.remove(cell);
	}

	public void removeSelectedCells() {
		Set<ICell> set = new HashSet<>(selectedCells);
		set.forEach(cell->cell.clear());
	}
	
	public void addTypeToSelectedCells(MapObjectType type){
		selectedCells.forEach(cell->cell.add(type));
	}

	public ICell getCell(GridPoint pt) {
		return myCells.get(pt);
	}

	public AGuiGaeController getController() {
		return myController;
	}

	@Override
	public int getLevelID() {
		return myLevelID;
	}
	
	protected void selectAll(){
		if(myCells.size()==selectedCells.size()){
			myCells.forEach((g,c)->c.deselect());
		}else{
			myCells.forEach((g,c)->c.select());
		}
	}
	
	
	

}
