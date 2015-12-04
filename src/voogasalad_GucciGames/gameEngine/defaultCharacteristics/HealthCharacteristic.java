package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public abstract class HealthCharacteristic extends AMapObjectCharacteristic{


	public HealthCharacteristic() {
		// TODO Auto-generated constructor stub
	}

	public abstract void changeHealth(double healthDiff);

	public abstract void resetHealth();

	public abstract double getCurrentHealth();
	public abstract void defineHealthValue(double healthValue);




}
