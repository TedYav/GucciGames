package voogasalad_GucciGames.gameplayer.gameloader;

import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;

public class GameLoader {

	public GameControllerInterface getController() {
		return new GameController(null);
	}

}
