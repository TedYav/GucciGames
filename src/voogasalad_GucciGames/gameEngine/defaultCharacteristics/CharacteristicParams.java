package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

public class CharacteristicParams {
	
	private String myName;
	private double myRange;
	private double myDamage;
	private int myMaxNumberOf;
	private boolean myIsAbleTo;
	

	public CharacteristicParams (String characteristicName, double range, double damage, int maxNumber, boolean can){
		this.myName = characteristicName;
		this.myRange = range;
		this.myDamage = damage;
		this.myMaxNumberOf = maxNumber;
		this.myIsAbleTo = can;
	}
	
	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public double getMyRange() {
		return myRange;
	}

	public void setMyRange(double myRange) {
		this.myRange = myRange;
	}

	public double getMyDamage() {
		return myDamage;
	}

	public void setMyDamage(double myDamage) {
		this.myDamage = myDamage;
	}

	public int getMyMaxNumberOf() {
		return myMaxNumberOf;
	}

	public void setMyMaxNumberOf(int myMaxNumberOf) {
		this.myMaxNumberOf = myMaxNumberOf;
	}

	public String getName(){
		return this.myName;
	}
	
	public boolean isAbleTo() {
		return myIsAbleTo;
	}

	public void setMyIsAbleTo(boolean myIsAbleTo) {
		this.myIsAbleTo = myIsAbleTo;
	}




}
