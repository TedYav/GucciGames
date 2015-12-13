package voogasalad_GucciGames.gameEngine.gamePlayer;

public class DefaultTurnDecider extends ATurnDecider {

	private int numPlayers;
	private TurnCounter turnCounter;
	private AllPlayers myPlayers;
	private GamePlayerPerson myActivePlayer;

	/**
	 * 
	 * @param numberPlayers
	 *            is the number of players (right now includes the neutral
	 *            player)
	 * @param currTurn
	 */
	public DefaultTurnDecider(AllPlayers players, TurnCounter turnCoun) {
		turnCounter = turnCoun;
		myPlayers = players;
		updateActivePlayer();
	}

	@Override
	public GamePlayerPerson getActivePlayer() {
		return myActivePlayer;
	}

	public void updateActivePlayer() {
		System.out.println("Current Turn Player ID is : " + decideTurn());
		myActivePlayer = myPlayers.getPlayerById(decideTurn());
	}

	public void setCurrentTurnPlayer(GamePlayerPerson myPlayer) {
		myActivePlayer = myPlayer;
	}

	public void setCurrentTurnPlayer(int id) {
		myActivePlayer = myPlayers.getPlayerById(id);
	}

	@Override
	public int decideTurn() {
		return myPlayers.getAllExistingIds()
				.get((turnCounter.getCurrentTurn() % (myPlayers.getNumberOfPlayers() - 1)) + 1);
	}

}
