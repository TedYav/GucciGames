package voogasalad_GucciGames.gameEngine;

/**
 * This class will specify whether this is a game online or on a single machine.
 * Note that this class will NOT store information about AI or teams or anything
 * like that.
 * 
 * @author Efe Aras
 *
 */
public abstract class GameNetworkFormat {

	/**
	 * This lets people know when the game is ready to be launched. (Default
	 * true in the single machine case, and clients will also return this as
	 * true)
	 */
	private boolean isReady;

	/**
	 * An engine is either going to be the server or the client. (or perhaps
	 * host a server?) (in single player, the single engine is going to be the
	 * server)
	 */
	private boolean isServer;

}
