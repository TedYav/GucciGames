package voogasalad_GucciGames.gameplayer.gameloader;

import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes.GameSceneInterface;

public class GameLoader {
    private GameControllerInterface myController;
    private GameDataInterface myData;
    
    public GameLoader(GameEngineToGamePlayerInterface e) {
        myController = new GameController(e);
    }
	public GameControllerInterface getController() {
		return myController;
	}
	public void reinitializeController(GameSceneInterface scene) {
	    myController.setScene(scene);
	}

}
