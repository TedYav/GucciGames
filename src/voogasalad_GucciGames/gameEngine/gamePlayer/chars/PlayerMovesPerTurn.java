package voogasalad_GucciGames.gameEngine.gamePlayer.chars;

public class PlayerMovesPerTurn extends APlayerChars {
	private int myNumberOfMoves = 1000;// if game user does not define number of
										// moves allowed for a player per game,
										// then the player gets default number
										// of moves = 1000
	private int moveCount = 0;

	public PlayerMovesPerTurn() {
		this.myNumberOfMoves = 3;
	}

	public PlayerMovesPerTurn(int numofMoves) {
		myNumberOfMoves = numofMoves;
	}



	public int getMyNumberOfMoves() {
		return myNumberOfMoves;
	}

	public void setMyNumberOfMoves(int numberOfMoves) {
		this.myNumberOfMoves = numberOfMoves;
	}

	public void updateMoves() {

		setMoveCount(getMoveCount() + 1);
	}

	public void reset() {
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
