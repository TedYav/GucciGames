// This entire file is part of my masterpiece.
// Efe Aras

package voogasalad_GucciGames.gameEngine.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This is the one of the roles that the GameNetworkActor can have. In this
 * role, it initiates a connection, and then begins, runs and ends a connection
 * as specified by its super class.
 *
 * You can look at the Java Socket Programming Examples page below for further
 * examples on networking.
 *
 * @see <a href="http://cs.lmu.edu/~ray/notes/javanetexamples/">Java Socket
 *      Programming Examples</a>
 *
 * @author Efe Aras
 *
 */
public class GameEngineClient extends GameNetworkRole {

	private int myPort;
	private String myServerAddress;

	/**
	 * Creates a client which has access to a GameNetworkEngineInterface. If
	 * this constructor is used, then it pulls its data from a configuration
	 * file found within the package specified by the superclass of this method.
	 * 
	 * 
	 * @param gameEngine
	 *            is an engine passed through the GameNetworkEngineInterface
	 */
	public GameEngineClient(GameNetworkEngineInterface gameEngine) {
		super(gameEngine);
		myServerAddress = getNetworkBundle().getString("defaultServerAddress");
		myPort = Integer.parseInt(getNetworkBundle().getString("defaultPort"));
	}

	/**
	 * 
	 * @param gameEngine is an engine passed through the GameNetworkEngineInterface
	 * @param ipAddress is an IP address passed into this method
	 */
	public GameEngineClient(GameNetworkEngineInterface gameEngine, String ipAddress) {
		this(gameEngine);
		myServerAddress = ipAddress;
	}

	/**
	 * Creates a client which has access to a GameNetworkEngineInterface. If
	 * this constructor is used, then it pulls its data from a configuration
	 * file found within the package specified by the superclass of this method.
	 * 
	 * @param gameEngine
	 *            is an engine passed through the GameNetworkEngineInterface
	 * @param ipAddress
	 * 			is the IP address of the host
	 * @param port
	 * 			is the port number of the host being connected to
	 */
	public GameEngineClient(GameNetworkEngineInterface gameEngine, String ipAddress, int port) {
		this(gameEngine, ipAddress);
		myPort = port;
	}

	@Override
	public void run() {

		try {
			Socket socket = new Socket(myServerAddress, myPort);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter myWriterToServer = new PrintWriter(socket.getOutputStream(), true);

			beginConnection();
			runConnection(in);
			endConnection(socket, in, myWriterToServer);

		} catch (UnknownHostException e) {
			getEngine().notifyEngine(new NetworkException(getNetworkBundle().getString("hostException")));
		} catch (IOException e) {
			getEngine().notifyEngine(new NetworkException(getNetworkBundle().getString("ioException")));
		}

	}

	@Override
	public GameNetworkRoleType getRoleType() {
		return GameNetworkRoleType.CLIENT;
	}
}
