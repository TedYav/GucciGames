package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.net.Socket;
import java.util.Optional;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

public abstract class GameNetworkRole implements Runnable {

	private GameEngine mySuperEngine;
	private Optional<GameEnginePlayer> myActor;
	private boolean interrupted;

	private ResourceBundle bundle;

	protected GameEngine getMySuperEngine() {
		return mySuperEngine;
	}

	public GameNetworkRole(GameEngine engine) {
		mySuperEngine = engine;
	}

	public abstract GameNetworkRoleType getRoleType();

	public void setActor(GameEnginePlayer gameEnginePlayer) {

		myActor = Optional.ofNullable(gameEnginePlayer);
	}

	public Optional<GameEnginePlayer> getActor() {
		return myActor;
	}

	protected void beginConnection() {
		// for now, do nothing
	}

	protected void runConnection(Reader in2) throws IOException {
		BufferedReader in = new BufferedReader(in2);
		while (!interrupted) {
			String input = in.readLine();
			interrupted = !(input == null);
			checkActorProtocols(in, input);
		}
	}

	private void checkActorProtocols(Reader in, String input) {
		myActor.ifPresent(e -> {
			e.checkInputProtocols(input, in);
			;
		});
	}

	protected void endConnection(Socket socket, Reader in, Writer myWriterToServer) throws IOException {
		socket.close();
		in.close();
		myWriterToServer.close();
	}

	public ResourceBundle getNetworkBundle() {
		return bundle;
	}

	protected boolean isInterrupted() {
		return interrupted;
	}

	public void setInterrupted(boolean interrupted) {
		this.interrupted = interrupted;
	}

}
