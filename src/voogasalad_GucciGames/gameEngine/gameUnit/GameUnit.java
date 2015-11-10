package voogasalad_GucciGames.gameEngine.gameUnit;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

public class GameUnit {

	private int myOwnerId;
	private GameUnitType myType;
	
	private int myX;
	private int myY;
	
	public GameUnit(int ownerId, GameUnitType type, int x, int y){
		setMyOwnerId(ownerId);
		setMyType(type);
		myX = x;
		myY = y;
	}

	public GameUnitType getMyType() {
		return myType;
	}

	public void setMyType(GameUnitType myType) {
		this.myType = myType;
	}

	public int getMyOwnerId() {
		return myOwnerId;
	}

	public void setMyOwnerId(int myOwnerId) {
		this.myOwnerId = myOwnerId;
	}
	
	public String toString(){
		return myType.toString() + " is at x: " + myX + " and y: " + myY;
	}

	public void performAction(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
}
