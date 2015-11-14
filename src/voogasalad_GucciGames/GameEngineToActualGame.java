package voogasalad_GucciGames;

import voogasalad_GucciGames.gameAuthoring.gui.map.IGUIMap;
import voogasalad_GucciGames.gameEngine.gameUnit.AbilityException;
import voogasalad_GucciGames.gameEngine.mapObject.IMapObjectAction;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;

public interface GameEngineToActualGame {

	// for all methods that say String actionType, use reflection

	/**
	 * This method starts the first turn
	 */
	public void initialize();

	/**
	 * 
	 * @param map -> interface 
	 */
	public void loadMap(IGUIMap map);
	
	/**
	 * This method finds the valid locations for a unit and returns an
	 * unmodifiable list of them.
	 * 
	 * @param actingUnit
	 *            : Unit to check where to go
	 * @param actionType
	 *            : Valid locations for a given action "Move" for movement,
	 *            "Attack" for attacking and abilityName for ability
	 */

	// may make this return an iterator or take in a lambda instead

	// TargetCoordinate class will have a list of Point2Ds
	public ATargetCoordinate getValidLocations(MapObject actingUnit, String actionType);

	/**
	 *
	 * This method ends the current turn, checks for end conditions and changes
	 * the active player.
	 */
	public void endTurn();

	/**
	 * 
	 * 
	 * This method performs an action specified by the acting unit and the name
	 * of the action. Use it for abilities that do not have a target (e.g. a tax
	 * collector increasing gold)
	 * 
	 * @param actingUnit is the unit acting
	 * @param actionType is the type of action
	 */
	public void performAction(MapObject actingUnit, IMapObjectAction actionType) throws AbilityException;

	/**
	 * This method performs an action specified by the acting unit, the name of
	 * the action and where the action is aimed at. A natural application to
	 * this will be the "Move" command, which picks an acting unit and moves it
	 * to the action target.
	 * 
	 * @param actingUnit is the unit acting
	 * @param actionType is the type of action
	 * @param actionTarget is where the action is going to happen
	 * 	 */
	public void performAction(MapObject actingUnit, IMapObjectAction actionType, ATargetCoordinate actionTarget) throws AbilityException;
	
	/**
	 * This method performs an action specified by the name of
	 * the action and where the action is aimed at. A natural application to
	 * this will be the "Build" command, which does not require a unit to perform.
	 * 
	 * @param actionType is the type of action
	 * @param actionTarget is where the action is going to happen
	 * 	 */
	public void performAction(IMapObjectAction actionType, ATargetCoordinate actionTarget) throws AbilityException;

	
	
	

}
