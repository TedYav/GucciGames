package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class TileCharacteristic extends AMapObjectCharacteristic{
	private boolean myCanMove = true;

	public TileCharacteristic(boolean canMove) {
		super();
		this.myCanMove = canMove;
	}
	
	public boolean canMove(){
		return this.myCanMove;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

}
