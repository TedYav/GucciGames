package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class MovableCharacteristic extends AMapObjectCharacteristic{
	private int NumberOfMoves = 1;// by default each  map object can move once per turn.
	private double MoveRange = 1;


	public MovableCharacteristic(){

	}

	public MovableCharacteristic(double range, int maxNumOfMoves){
		MoveRange = range;
		NumberOfMoves = maxNumOfMoves;
	}

	public int getNumberOfMoves() {
		return NumberOfMoves;
	}

	public double getMoveRange() {
		return MoveRange;
	}


}
