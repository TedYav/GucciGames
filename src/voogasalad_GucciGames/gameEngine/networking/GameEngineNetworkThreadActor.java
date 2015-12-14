// This entire file is part of my masterpiece.
// Efe Aras

package voogasalad_GucciGames.gameEngine.networking;

import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This is an implementation of a GameEngineNetworkActionInterface that allows
 * this actor to run on a separate thread than the main program.
 * 
 * <br>
 * Note that this actor is designed to run on a separate thread from your
 * frontend thread, so when you run it, you can run it in a different thread
 * since it implements Runnable. <br>
 * <br>
 * Ideally, the engine using the network actor would first initialize the
 * network actor in its constructor, and then assign it a role and different
 * protocols when the user makes a decision. This actor comes with a NullRole,
 * which is compatible with Single Player games, so it will not cause any errors
 * if it is initialized even in a Single Player scenario. <br>
 * 
 * Also look at {@link GameEngineNetworkActorInterface} to see example usage for
 * this class.
 * 
 * @author Efe Aras
 *
 */
public class GameEngineNetworkThreadActor implements GameEngineNetworkActorInterface, Runnable {

	private GameNetworkEngineInterface mySuperEngine;
	private Set<GameInformationProtocol> myProtocols;
	private GameNetworkRole myRole;

	/**
	 * Allows constructing an actor for a given engine
	 * 
	 * @param engine
	 */
	public GameEngineNetworkThreadActor(GameNetworkEngineInterface engine) {
		mySuperEngine = engine;
		myRole = new GameEngineNullRole(engine);
	}

	@Override
	public boolean addProtocol(GameInformationProtocol protocol) {
		return myProtocols.add(protocol);
	}

	@Override
	public boolean checkInputProtocols(String tag, Reader in) {
		return checkProtocols(tag, inputConsumer(in));
	}

	@Override
	public boolean checkProtocols(String tag, Consumer<? super GameInformationProtocol> protocolConsumer) {
		Set<GameInformationProtocol> myApplicableProtocols = collectApplicableProtocols(tag);
		myApplicableProtocols.stream().forEach(protocolConsumer);
		return myApplicableProtocols.size() == 1;
	}

	private Consumer<? super GameInformationProtocol> inputConsumer(Reader in) {
		return e -> e.receiveMessage(in);
	}

	@Override
	public boolean sendOutputMessage(String tag, String message, Writer out) {
		return checkProtocols(tag, writerConsumer(message, out));
	}

	@Override
	public boolean sendInformation(String tag, String message) {
		return sendInformation(tag, message, mySuperEngine.getPlayerIDs());
	}

	@Override
	public boolean sendInformation(String tag, String message, Collection<Integer> playerID) {
		return checkProtocols(tag, sendMessageConsumer(message, playerID));
	}

	private Consumer<? super GameInformationProtocol> sendMessageConsumer(String message,
			Collection<Integer> playerID) {
		return e -> e.sendMessage(message, playerID);
	}

	private Consumer<? super GameInformationProtocol> writerConsumer(String message, Writer out) {
		return e -> e.sendMessage(out, message);
	}

	private Set<GameInformationProtocol> collectApplicableProtocols(String tag) {
		return myProtocols.stream().filter(hasSameTag(tag)).collect(Collectors.<GameInformationProtocol> toSet());
	}

	private Predicate<GameInformationProtocol> hasSameTag(String tag) {
		return p -> p.getTag().equals(tag);
	}

	@Override
	public void assignRole(GameNetworkRole role) {
		myRole = role;
		role.setActor(this);
	}

	public void run() {
		myRole.run();
	}

	@Override
	public void play() {
		run();
	}

}
