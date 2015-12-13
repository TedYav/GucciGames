package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public class CellUnit {

	private String myImage;
	private MapInterface myMap;
	private MapCellInterface myCell;

	private PlayerMapObjectInterface myUnit;

	public CellUnit(MapInterface map, MapCellInterface cell, PlayerMapObjectInterface unit) {
		myMap = map;
		myCell = cell;
		myUnit = unit;
	}

	public void updateImage(String image) {
		myImage = image;
	}

	public String getImage() {
		return myImage;
	}

}
