package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class TileCharacteristic extends AMapObjectCharacteristic{
	private boolean myCanMove;

	public TileCharacteristic(boolean canMove) {
		super();
		this.myCanMove = canMove;
	}
	
	public TileCharacteristic(){
		this.myCanMove = true;
	}
}
