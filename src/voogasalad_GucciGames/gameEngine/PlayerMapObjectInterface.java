package voogasalad_GucciGames.gameEngine;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;
import voogasalad_GucciGames.gameEngine.objectActions.IGamePlayerMapObjectAction;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;

public interface PlayerMapObjectInterface {

	/**
	 * This method right now returns a Map from the String name of the attribute
	 * to String representation of all of the attributes by calling their
	 * toString() method. (e.g. One entry of the map could be "Health" ->
	 * "Max Health: 100\nCurrentHealth : 50") This information can be accessed
	 * by accessing the attributes themselves.
	 *
	 * @return a map from a name of the attribute to the String representation
	 *         of the attribute.
	 */
	public Map<String, String> getAttributeStrings();

	/**
	 * This method gives the name of the type of the object. (e.g. Soldier).
	 * Note that this name does not need to be unique; there can be multiple
	 * "Soldier"s on the map.
	 *
	 * @return a String which gives the name of the map object
	 */
	public String getName();

	/**
	 * This method returns the image path for the object.
	 *
	 * @return a String which is the relative image path from src to the image.
	 */
	public String getImageURI();

	/**
	 * This method returns the layer of the object. The smaller the layer is,
	 * the earlier it will be drawn on the map. (e.g. Tiles for a game can have
	 * layer 0, structures can have layer 1, units can have layer 2).
	 *
	 * @return a number which represents where the map object is going to be
	 *         drawn.
	 */
	public int getLayer();

	/**
	 * This method returns a List of Strings as the names of the possible
	 * actions that this map object can perform. Note that modifying this list
	 * will NOT modify any of the existing actions. (e.g. deleting an action
	 * from this list will not delete an action from the actual object.)
	 *
	 * @return a List of Strings which represents what the map object can do as
	 *         actions.
	 */
	public List<String> getActionNames();


	/**
	 * This method returns the owner id of the current map object.
	 *
	 * @return the owner ID of the map object
	 */
	public int getOwnerID();

	/**
	 * This method returns the list of Strings which represent the names of the
	 * characteristics that the map object has. Note that modifying this list
	 * will NOT modify any of the existing characteristics. (e.g. deleting a
	 * characteristic from this list will not delete a characteristic from the
	 * actual object.)
	 *
	 * @return a List of Strings which represents what the map object can have
	 *         as characteristics.
	 */
	public List<String> getAllCharacteristicNames();

	// maybe modify this to put an interface on top of characteristics?
	/**
	 * This method returns a specific characteristic that the object has.
	 * Ideally, the user should already have a list of mappings of
	 * characteristic names to the actual class names (perhaps from a resource
	 * file), and thus should be able to type-cast it to the specific
	 * characteristic.
	 *
	 * Note that for custom characteristics, the standard name will be the class
	 * name without an appended Characteristic.java extension; so you can access
	 * the characteristic directly by type-casting to the name. (So a custom
	 * characteristic called Poisonous would map "Poisonous" to a class called
	 * PoisonousCharacteristic.java)
	 *
	 * @param name
	 *            name of the characteristic
	 * @return the characteristic corresponding to it
	 */
	public AMapObjectCharacteristic getCharacteristic(String name);

	/**
	 * Returns the coordinate that the object is on. If the object isn't on the
	 * map, it should return an empty target coordinate.
	 *
	 * @return the coordinate(s) that the map object is on.
	 */
	public ATargetCoordinate getCoordinate();

	@Deprecated
	/**
	 * Instead of this method, the user is encouraged to access the
	 * characteristic that they want, and access the respective method.
	 *
	 * @return
	 */
	public boolean isDead();

	@Deprecated
	/**
	 * Use performRequest(String name) instead to get ATargetCoordinate (which
	 * could be single or multiple)
	 *
	 * @param name
	 * @return
	 */
	public List<ATargetCoordinate> getActionTargets(String name);

	/**
	 * Tells the front end whether the object should be removed from the map. (A
	 * simple implementation of this would be to check the "Health"
	 * characteristic of the map object, and then check if it is dead.)
	 *
	 * @return true if the object will be removed, false otherwise.
	 */
	public boolean isRemoved();

	/**
	 * @return
	 */
	int getPlayerID();

	/**
	 * @param name
	 * @return
	 */
	IGamePlayerMapObjectAction getAction(String name);

	public ChangedParameters performAction(String action, ATargetCoordinate coor);
	
	public GridCoordinateParameters performRequest(String action);
	
}
