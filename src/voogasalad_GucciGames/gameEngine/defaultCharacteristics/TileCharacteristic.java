package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class TileCharacteristic extends AMapObjectCharacteristic{
	private boolean CanMove = true;


	public TileCharacteristic(boolean canMove) {

		this.CanMove = canMove;
	}
	
	public boolean getCanMove(){
		return this.CanMove;
	}

	public TileCharacteristic(){
	}

}
