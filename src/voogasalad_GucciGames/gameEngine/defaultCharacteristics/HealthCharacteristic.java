package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.MapObjectCharacteristic;

public abstract class HealthCharacteristic extends MapObjectCharacteristic{

	
	public abstract void changeHealth(double healthDiff);
	
	public abstract void resetHealth();
	
	public abstract double getCurrentHealth();


	
}
