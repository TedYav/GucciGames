package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class MovableCharacteristic extends AMapObjectCharacteristic{
	private int myNumberOfMoves = 1;// by default each  map object can move once per turn.
	private double myRange = 1;


	public MovableCharacteristic(){

	}

	public MovableCharacteristic(double range, int maxNumOfMoves){
		myRange = range;
		myNumberOfMoves = maxNumOfMoves;
	}

	public int getNumberOfMoves() {
		return myNumberOfMoves;
	}

	public double getRange() {
		return myRange;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Range = " + myRange;
	}


}
