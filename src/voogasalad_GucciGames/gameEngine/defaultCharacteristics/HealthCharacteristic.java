package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public abstract class HealthCharacteristic extends AMapObjectCharacteristic{

	
	public abstract void changeHealth(double healthDiff);
	
	public abstract void resetHealth();
	
	public abstract double getCurrentHealth();


	
}
