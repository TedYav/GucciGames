package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class MovableCharacteristic extends AMapObjectCharacteristic{
	private int myNumberOfMoves;
	private double myRange;
	
	public MovableCharacteristic(int moves, double range){
		myNumberOfMoves = moves;
		myRange = range;
	}
	
	public int getNumberOfMoves() {
		return myNumberOfMoves;
	}
	public void setNumberOfMoves(int numberOfMoves) {
		this.myNumberOfMoves = numberOfMoves;
	}
	
	public double getRange() {
		return myRange;
	}
	public void setRange(double range) {
		this.myRange = range;
	}

}
