package voogasalad_GucciGames.gameEngine;

import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.CommunicationParams.ActionToGamePlayerParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;

public interface GameEngineToGamePlayerInterface {

	/**
	 * This is the name of the game running in this instance of the engine.
	 * 
	 * @return a String to represent the name of the game.
	 */
	public String getGameName();

	// make this list unmodifiable later on.
	/**
	 * This returns the list of map objects that the author has created, and it
	 * just gives a list of them to the Game Player. Note that it is given in
	 * the form of a PlayerMapObjectInterface.
	 * 
	 * @return a List of all the MapObjects on the map through the
	 *         PlayerMapObjectInterface.
	 */
	public List<PlayerMapObjectInterface> getInitialState();

	/**
	 * This is the method that the front end has to call when they are ending a
	 * turn. Fundamentally speaking, this method should check whether certain
	 * outcomes have been achieved by checking their conditions and advance a
	 * turn counter. This method also returns a GameParametersInterface which
	 * contains information about whether the game has ended or not.
	 * 
	 * 
	 */
	public GameParametersInterface endTurn();

	@Deprecated
	/**
	 * Instead of this method, the user is encouraged to access the current game
	 * parameters and then access the turn player ID from there.
	 * 
	 * @return
	 */
	public int getTurnPlayerID();

	@Deprecated
	/**
	 * You can access the
	 * @param action
	 * @param mapObject
	 * @return
	 */
	public GridCoordinateParameters getPossibleCoordinates(String action, PlayerMapObjectInterface mapObject);

	public ActionToGamePlayerParameters performAction(String action, PlayerMapObjectInterface mapObject,
			ATargetCoordinate target);

	public int getMapWidth();

	public int getMapHeight();

	public GameParametersInterface getGameParameters();
}
