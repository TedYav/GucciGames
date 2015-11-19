package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class AttackCharacteristic extends AMapObjectCharacteristic{
	private double myRange = 1;
	private double myDamage = 1;
	private int maxNumberOfAttacks = 1;
	private int currentNumberOfAttacks = 0;
	
	
	public AttackCharacteristic(CharacteristicParams charParams){
		super(charParams);
		myRange = charParams.getMyRange();
		myDamage = charParams.getMyDamage();
		maxNumberOfAttacks = charParams.getMyMaxNumberOf();
	}
	
	public AttackCharacteristic(double range, double damage, int maxNum){
		super(null);
		myRange = range;
		myDamage = damage;
		maxNumberOfAttacks = maxNum;
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
	
	public void set(List<Integer> values){
		myRange = values.get(0);
		myDamage = values.get(1);
		maxNumberOfAttacks = values.get(2);
	}
	
}
