// This entire file is part of my masterpiece.
// Efe Aras

package voogasalad_GucciGames.gameEngine.networking;

import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

/**
 * This is the class that acts as a network agent in this game. To make the game
 * network-compatible, you have to instantiate a network actor on different JVMs
 * and assign them different GameNetworkRoles. <br>
 * <br>
 * Example usage: <br>
 * ... <br>
 * Game loader loads which protocols to use for the game and calls
 * myActor.addProtocol(GameInformationProtocol) <br>
 * ... <br>
 * User selects whether they want to be host or client <br>
 * myActor.assignRole(GameNetworkRole)<br>
 * ... <br>
 * User starts the game <br>
 * myActor.play();
 * 
 * 
 * 
 * 
 * @author Efe Aras
 *
 */
public interface GameEngineNetworkActorInterface {
	
	/**
	 * Adds a protocol to use for the current game.
	 * 
	 * @see <a href=
	 *      "http://docs.oracle.com/javase/7/docs/api/java/util/Set.html#add(E)">
	 *      Java Set add</a> to understand why the boolean is returned.
	 * 
	 * 
	 * @param protocol
	 *            is the protocol to be added
	 * @return
	 */
	public boolean addProtocol(GameInformationProtocol protocol);

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
	public boolean checkInputProtocols(String tag, Reader in);

	/**
	 * Checks the protocols for a given consumer and a tag. If new methods are
	 * to be added to the GameInformationProtocol, then this method can be used
	 * to query all the protocols and execute them if the tag of the protocol
	 * matches.
	 * 
	 * 
	 * 
	 * @param tag
	 *            is the String tag of the protocols
	 * @param protocolConsumer
	 *            a FunctionalInterface that accepts in an input and returns no
	 *            output.
	 * @return true if there was only a single protocol that had the given tag
	 *         false if otherwise
	 */
	boolean checkProtocols(String tag, Consumer<? super GameInformationProtocol> protocolConsumer);
	
	/**
	 * 
	 * This method allows different roles to query the protocols and by passing
	 * in an output writer, a message can be sent.
	 * 
	 * Different networking classes, like protocols and roles, are expected to
	 * access this method.
	 * 
	 * Look at {@link GameEngineNetworkThreadActor#checkProtocols(String, Consumer)
	 * checkProtocols} method to understand the inputs and the outputs.
	 * 
	 */
	public boolean sendOutputMessage(String tag, String message, Writer out);
	
	/**
	 * This method allows sending an information with a certain tag to all the
	 * players in the game. Note that the tag for this should be pulled from the
	 * .properties file found in the networking package.
	 * 
	 * See also {@link #sendInformation(String, String, Set) sendMessage(tag,
	 * message, playerIDs)} for a general overview of the sendMessage method.
	 * 
	 * @param tag
	 * @param message
	 * @return
	 */
	public boolean sendInformation(String tag, String message);
	
	
	/**
	 * 
	 * This method allows sending some information to clients with the IDs
	 * specified in the input collection.
	 * 
	 * <br>
	 * Example usage: <br>
	 * <br>
	 * myActor.sendMessage(bundle.getString("chat"), "hello", {1, 3, 5})<br>
	 * <br>
	 * will send the message "hello" over the chat client to players with IDs 1,
	 * 3, 5 <br>
	 * Note that this method will not wait for a response from the server and
	 * the clients and thus its truth value is independent from whether the
	 * other players received the message or not.
	 * 
	 * @param tag
	 *            is the tag of the information
	 * @param message
	 *            is the information to send
	 * @param playerID
	 *            is a set of playerIDs to send the message to
	 * @return true if a single protocol with the tag was found false otherwise
	 */
	public boolean sendInformation(String tag, String message, Collection<Integer> playerID);
	
	
	/**
	 * Assigns a role to this actor. Additionally, assigns an actor to that role
	 * automatically.
	 * 
	 * @param role
	 *            is the role this actor will take on.
	 */
	public void assignRole(GameNetworkRole role);
	
	/**
	 * Asks the actor to play its role.
	 */
	public void play();
	

}
