package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents;

import java.util.Observable;
import java.util.Observer;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;

public class CellUnit{

	private String myImage;
	private MapInterface myMap;
	private MapCell myCell;
	
	private PlayerMapObjectInterface myUnit;
		
	public CellUnit(MapInterface map, MapCell cell, PlayerMapObjectInterface unit){
		myMap = map;
		myCell = cell;
		myUnit = unit;
	}
	
	public void updateImage(String image){
		myImage = image;
	}
	
	public String getImage(){
		return myImage;
	}

}
