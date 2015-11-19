package voogasalad_GucciGames.gameEngine.gamePlayer;

public abstract class ATurnDecider {

	
	/**
	 * Return the player who is going to take a turn
	 * @return
	 */
	public abstract GamePlayerPerson getActivePlayer();

	public abstract int decideTurn();
	
	public abstract void updateActivePlayer();
	
}
