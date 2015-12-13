package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class HealthCharacteristic extends AMapObjectCharacteristic {

	private double CurrentHealth;
	private double MaxHealth = 100;

	public HealthCharacteristic() {
	}

	public HealthCharacteristic(double maxHealthPoints) {
		defineHealthValue(maxHealthPoints);
	}

	public void changeHealth(double healthDiff) {
		CurrentHealth -= healthDiff;
	}

	public void resetHealth() {
		CurrentHealth = MaxHealth;
	}

	public double getCurrentHealth() {
		return CurrentHealth;
	}

	public double getMaxHealth() {
		return MaxHealth;
	}

	public void defineHealthValue(double healthValue) {
		MaxHealth = healthValue;
		CurrentHealth = healthValue;

	}

	public boolean isDead() {
		return CurrentHealth <= 0;
	}

	/*
	 * @Override public String toString() { // TODO Auto-generated method stub
	 * return "Current Health = " + myCurrentHealth; }
	 */

}
