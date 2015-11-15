package voogasalad_GucciGames.gameEngine.gamePlayer;

public class DefaultTurnDecider extends ATurnDecider {

	private int numPlayers;
	private TurnCounter turnCounter;

	/**
	 * 
	 * @param numberPlayers
	 *            is the number of players (right now includes the neutral
	 *            player)
	 * @param currTurn
	 */
	public DefaultTurnDecider(int numberPlayers, TurnCounter turnCoun) {
		numPlayers = numberPlayers;
		turnCounter = turnCoun;

	}

	@Override
	public int decideTurn() {

		// (-1 is there in the mod operation because player at index 0 doesn't
		// take a turn (neutral) and +1 is just for math to work out
		return (turnCounter.getCurrentTurn() % (numPlayers - 1)) + 1;

	}

}
