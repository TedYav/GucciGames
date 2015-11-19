package voogasalad_GucciGames.gameEngine.gamePlayer;

public class MovablePlayerCharacteristic extends AGamePlayerPersonCharacteristic{
	private int myNumberOfMoves =-1;
	private int moveCount = 0;


	public MovablePlayerCharacteristic(int numMoves){
		myNumberOfMoves = numMoves;
	}
	
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

		setMoveCount(getMoveCount() + 1);
	}
	
	public void reset(){
		System.out.println("updating moves");
		setMoveCount(0);
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

}
