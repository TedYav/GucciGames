package voogasalad_GucciGames.gameplayer.gameloader;

import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;

public class GameLoader {
    private GameControllerInterface myController;
    private GameDataInterface myData;
    
    public GameLoader(GameEngineToGamePlayerInterface e) {
        myController = new GameController(e);
        System.out.println(myController.getEngine().getInitialState());
    }
	public GameControllerInterface getController() {
		return myController;
	}

}
