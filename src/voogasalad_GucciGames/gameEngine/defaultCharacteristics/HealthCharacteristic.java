package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class HealthCharacteristic extends AMapObjectCharacteristic {

	private double myCurrentHealth;
	private double myMaxHealth = 100;

	public HealthCharacteristic() {
	}

	public HealthCharacteristic(double maxHealthPoints) {
		defineHealthValue(maxHealthPoints);
	}

	public void changeHealth(double healthDiff) {
		myCurrentHealth -= healthDiff;
	}

	public void resetHealth() {
		myCurrentHealth = myMaxHealth;
	}

	public double getCurrentHealth() {
		return myCurrentHealth;
	}

	public void defineHealthValue(double healthValue) {
		myMaxHealth = healthValue;
		myCurrentHealth = healthValue;

	}

	public boolean isDead() {
		return myCurrentHealth <= 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Current Health = " + myCurrentHealth;
	}

}
