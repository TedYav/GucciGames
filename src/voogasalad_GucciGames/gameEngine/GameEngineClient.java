package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;

/**
 * This is the wrapper for the client. May be merged with GameEngineServer later
 * on.
 *
 * @author Efe Aras
 *
 */
public class GameEngineClient extends GameNetworkRole implements Runnable {

	private int myPort;
	private String myServerAddress;

	public GameEngineClient(GameEngine gameEngine) {
		super(gameEngine);
		myServerAddress = getNetworkBundle().getString("defaultServerAddress");
		myPort = Integer.parseInt(getNetworkBundle().getString("defaultPort"));
	}

	public GameEngineClient(GameEngine gameEngine, String ipAddr) {
		this(gameEngine);
		myServerAddress = ipAddr;
	}

	public GameEngineClient(GameEngine gameEngine, String ipAddr, int port) {
		this(gameEngine, ipAddr);
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
			getMySuperEngine().notifyEngine(new NetworkException(getNetworkBundle().getString("hostException")));
		} catch (IOException e) {
			getMySuperEngine().notifyEngine(new NetworkException(getNetworkBundle().getString("ioException")));
		}

	}

	@Override
	public GameNetworkRoleType getRoleType() {
		return GameNetworkRoleType.CLIENT;
	}
}
