package voogasalad_GucciGames.gameEngine.gamePlayer;

public class MovablePlayerCharacteristic extends GamePlayerPerson{
	private int myNumberOfMoves;
	private int moveCount = 0;


	public MovablePlayerCharacteristic(UnitCollection units, int playerId) {
		super(units, playerId);
		myNumberOfMoves = -1;
		// TODO Auto-generated constructor stub
	}
	
	public int getMyNumberOfMoves() {
		return myNumberOfMoves;
	}

	public void setMyNumberOfMoves(int numberOfMoves) {
		this.myNumberOfMoves = numberOfMoves;
	}
	
	public void updateMoves(){
		moveCount++;
	}

}
