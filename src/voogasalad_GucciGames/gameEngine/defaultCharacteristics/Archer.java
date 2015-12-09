package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

/**
 *
 * @author Sally Al
 *
 */
public class Archer extends AMapObjectCharacteristic{
	private int myRange= 1;
	public Archer(int range){
		myRange = range;

	}
	public int getMyRange() {
		return myRange;
	}
	public void setMyRange(int myRange) {
		this.myRange = myRange;
	}

}
