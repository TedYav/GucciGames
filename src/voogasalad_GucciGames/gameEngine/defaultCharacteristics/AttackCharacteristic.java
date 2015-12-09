package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class AttackCharacteristic extends AMapObjectCharacteristic {
	private double AttackRange = 1;
	private double Damage = 1;
	private int MaxNumberOfAttacks = 1;
	private int CurrentNumberOfAttacks = 0;

	public AttackCharacteristic() {

	}

	public AttackCharacteristic(double range, double damage, int maxNum) {
		AttackRange = range;
		Damage = damage;
		MaxNumberOfAttacks = maxNum;
	}

	public double getAttackRange() {
		return AttackRange;
	}

	public double getDamage() {
		return Damage;
	}

	public int getCurrentNumberOfAttacks() {
		return CurrentNumberOfAttacks;
	}

	public int getMaxNumberOfAttacks() {
		return MaxNumberOfAttacks;
	}

	public void updateAttackCount() {
		CurrentNumberOfAttacks++;
	}

	public void reset() {
		CurrentNumberOfAttacks = 0;
	}

	public void set(List<Integer> values) {
		AttackRange = values.get(0);
		Damage = values.get(1);
		MaxNumberOfAttacks = values.get(2);
	}

}
