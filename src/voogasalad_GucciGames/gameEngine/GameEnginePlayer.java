package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

//gameEngineNetworkActor
public class GameEnginePlayer implements Runnable {

	private GameEngine mySuperEngine;
	private Set<GameInformationProtocol> myProtocols;
	private GameNetworkRole myRole;

	public GameEnginePlayer(GameEngine engine) {
		mySuperEngine = engine;
	}

	/**
	 * Checks the protocols that this actor has to see which one has the
	 * necessary tag to take in the input. If any of them has the tag, then the
	 * method proceeds to read through the Reader.
	 * 
	 * Note that after this method is called, if the method is successful, then
	 * the input reader will have "moved on"; if you want to store a copy of
	 * your Reader, do so before calling this method.
	 * 
	 * Note that this method is expected to return only one protocol when called
	 * with a certain tag. If that was not the case, this method will return
	 * false. It will also return false if no protocols were found with that
	 * tag.
	 * 
	 * @param tag
	 * @param in
	 * @return
	 */
	protected boolean checkInputProtocols(String tag, Reader in) {
		return checkProtocols(tag, inputConsumer(in));
	}

	/**
	 * Checks the protocols for a given consumer and a tag. If new methods are
	 * to be added to the GameInformationProtocol, then this method can be used
	 * to query all the protocols and execute them if the tag of the protocol
	 * matches.
	 * 
	 * 
	 * 
	 * @param tag
	 * @param myProtocol
	 * @return
	 */
	protected boolean checkProtocols(String tag, Consumer<? super GameInformationProtocol> myProtocol) {
		Set<GameInformationProtocol> myApplicableProtocols = collectApplicableProtocols(tag);
		myApplicableProtocols.stream().forEach(myProtocol);
		return myApplicableProtocols.size() == 1;
	}

	private Consumer<? super GameInformationProtocol> inputConsumer(Reader in) {
		return e -> e.receiveMessage(in);
	}

	/**
	 * 
	 * This method allows different roles to query the protocols and by passing
	 * in an output writer, a message can be sent.
	 * 
	 * @param tag
	 * @param message
	 * @param out
	 * @return
	 */
	protected boolean sendOutputMessage(String tag, String message, Writer out) {
		return checkProtocols(tag, outputConsumer(message, out));
	}

	public boolean sendMessage(String tag, String message) {
		return sendMessage(tag, message, mySuperEngine.getPlayerIDs());
	}

	public boolean sendMessage(String tag, String message, Set<Integer> playerID) {
		
		//get an unmodifiable map of playerID -> output streams
		
		playerID.stream();
		
		return false;
	}

	private Consumer<? super GameInformationProtocol> outputConsumer(String message, Writer out) {
		return e -> e.sendMessage(out, message);
	}

	private Set<GameInformationProtocol> collectApplicableProtocols(String tag) {
		return myProtocols.stream().filter(hasSameTag(tag)).collect(Collectors.<GameInformationProtocol> toSet());
	}

	private Predicate<GameInformationProtocol> hasSameTag(String tag) {
		return p -> p.getTag().equals(tag);
	}

	protected GameEngine getEngine() {
		return mySuperEngine;
	}

	public void assignRole(GameNetworkRole role) {
		myRole = role;
		role.setActor(this);
	}

	@Override
	public void run() {
		myRole.run();
	}

}
