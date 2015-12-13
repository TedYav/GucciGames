package voogasalad_GucciGames.gameplayer.eventhandler;

import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public abstract class GameEventHandler {

	protected GameControllerInterface myController;

	public GameEventHandler(GameControllerInterface controller) {
		myController = controller;
	}

}
