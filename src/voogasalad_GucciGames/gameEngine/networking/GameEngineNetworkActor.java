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

public class GameEngineNetworkActor implements GameEngineNetworkActorInterface, Runnable {

	private GameNetworkEngineInterface mySuperEngine;
	private Set<GameInformationProtocol> myProtocols;
	private GameNetworkRole myRole;

	public GameEngineNetworkActor(GameNetworkEngineInterface engine) {
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

}
