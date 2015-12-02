package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class MovableCharacteristic extends AMapObjectCharacteristic{
	private int myNumberOfMoves = 1;
	private double myRange = 1;
	
	public MovableCharacteristic(){
		CharacteristicParams charParams = new CharacteristicParams();
		myNumberOfMoves = charParams.getMyMaxNumberOf();
		myRange = charParams.getMyRange();
	}
	
	public MovableCharacteristic(double range, int maxNumOfMoves){
		CharacteristicParams charParams = new CharacteristicParams();
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
