
package voogasalad_GucciGames.gameEngine.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class provides certain utility methods for different roles to use. This
 * is also the super class of all the network roles. Note that while a role can
 * exist without an actor, some of its methods will not do anything without it
 * since the actor has access to the protocols needed. A GameNetworkRole should
 * 
 * Note that it is possible to assign an actor to this role whenever.
 * 
 * @author Efe Aras
 *
 */
public abstract class GameNetworkRole implements Runnable {

	private GameNetworkEngineInterface mySuperEngine;
	private Optional<GameEngineNetworkActorInterface> myActor;
	private boolean interrupted;

	private ResourceBundle bundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameEngine.networking.config.NetworkDefaults");

	/**
	 * 
	 * @return
	 */
	protected GameNetworkEngineInterface getEngine() {
		return mySuperEngine;
	}

	/**
	 * 
	 * @param engine
	 */
	public GameNetworkRole(GameNetworkEngineInterface engine) {
		mySuperEngine = engine;
	}

	/**
	 * 
	 * @return
	 */
	public abstract GameNetworkRoleType getRoleType();

	/**
	 * 
	 * @param gameEnginePlayer
	 */
	public void setActor(GameEngineNetworkActorInterface gameEnginePlayer) {

		myActor = Optional.ofNullable(gameEnginePlayer);
	}

	/**
	 * 
	 * @return
	 */
	public Optional<GameEngineNetworkActorInterface> getActor() {
		return myActor;
	}

	/**
	 * 
	 */
	protected void beginConnection() {
		// for now, do nothing
	}

	/**
	 * 
	 * @param in2
	 * @throws IOException
	 */
	protected void runConnection(Reader in2) throws IOException {
		BufferedReader in = new BufferedReader(in2);
		while (!interrupted) {
			String input = in.readLine();
			interrupted = !(input == null);
			checkActorProtocols(in, input);
		}
	}

	private void checkActorProtocols(Reader in, String input) {
		myActor.ifPresent(e -> e.checkInputProtocols(input, in));
	}

	/**
	 * 
	 * @param socket
	 * @param in
	 * @param myWriterToServer
	 * @throws IOException
	 */
	protected void endConnection(Socket socket, Reader in, Writer myWriterToServer) throws IOException {
		socket.close();
		in.close();
		myWriterToServer.close();
	}

	/**
	 * 
	 * @return
	 */
	protected ResourceBundle getNetworkBundle() {
		return bundle;
	}

	/**
	 * 
	 * @return
	 */
	protected boolean isInterrupted() {
		return interrupted;
	}

	/**
	 * 
	 * @param interrupted
	 */
	public void setInterrupted(boolean interrupted) {
		this.interrupted = interrupted;
	}

}
