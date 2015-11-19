package voogasalad_GucciGames.gameEngine.gamePlayer;

public class MovablePlayerCharacteristic extends AGamePlayerPersonCharacteristic{
	private int myNumberOfMoves =-1;
	private int moveCount = 0;


	/*public MovablePlayerCharacteristic(UnitCollection units, int playerId) {
		super(units, playerId);
		myNumberOfMoves = -1;
		// TODO Auto-generated constructor stub
	}*/

	public int getMyNumberOfMoves() {
		return myNumberOfMoves;
	}

	public void setMyNumberOfMoves(int numberOfMoves) {
		this.myNumberOfMoves = numberOfMoves;
	}

	public void updateMoves(){
		moveCount++;
	}
	
	public void reset(){
		moveCount = 0;
	}

}
