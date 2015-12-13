package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class TileCharacteristic extends AMapObjectCharacteristic {
	private int CanMove = 1;

	public TileCharacteristic(int canMove) {
		System.out.println("Made Tile Characteristic.");
		this.CanMove = canMove;
	}

	public int getCanMove() {
		return this.CanMove;
	}

	public TileCharacteristic() {
		System.out.println("Made Tile Characteristic.");
	}

}
