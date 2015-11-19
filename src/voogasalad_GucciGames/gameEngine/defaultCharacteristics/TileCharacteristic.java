package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class TileCharacteristic extends AMapObjectCharacteristic{
	private boolean myCanMove = true;

	public TileCharacteristic(CharacteristicParams charParams) {
		super(charParams);
		this.myCanMove = charParams.isAbleTo();
	}
	
	public boolean canMove(){
		return this.myCanMove;
	}

	public TileCharacteristic(){
		super(null);
	}

}
