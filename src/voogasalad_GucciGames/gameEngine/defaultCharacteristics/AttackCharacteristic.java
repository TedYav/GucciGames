package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class AttackCharacteristic extends AMapObjectCharacteristic{
	private double myRange;
	private double myDamage;
	
	public AttackCharacteristic(double range, double damage){
		myRange = range;
		myDamage = damage;
	}
	
	public double getRange() {
		return myRange;
	}
	
	public double getDamage() {
		return myDamage;
	}
	
}
