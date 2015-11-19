package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class MovableCharacteristic extends AMapObjectCharacteristic{
	private int myNumberOfMoves = 1;
	private double myRange = 1;
	
	public MovableCharacteristic(int moves, double range){
		myNumberOfMoves = moves;
		myRange = range;
	}
	
	public int getNumberOfMoves() {
		return myNumberOfMoves;
	}
	
	public double getRange() {
		return myRange;
	}


}
