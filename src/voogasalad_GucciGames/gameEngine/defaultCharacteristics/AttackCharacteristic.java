package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class AttackCharacteristic extends AMapObjectCharacteristic{
	private double myRange;
	private double myDamage;
	private int maxNumberOfAttacks = 1;
	private int currentNumberOfAttacks = 0;
	
	public AttackCharacteristic(double range, double damage, int maxNumber){
		myRange = range;
		myDamage = damage;
		maxNumberOfAttacks = maxNumber;
	}
	
	public double getRange() {
		return myRange;
	}
	
	public double getDamage() {
		return myDamage;
	}
	
	public int getCurrentNumberOfAttacks(){
		return currentNumberOfAttacks;
	}
	
	public int getMaxAttacks(){
		return maxNumberOfAttacks;
	}
	
	public void updateAttackCount(){
		currentNumberOfAttacks++;
	}
	
	public void reset(){
		currentNumberOfAttacks = 0;
	}
	
}
