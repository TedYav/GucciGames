package voogasalad_GucciGames.gameplayer.gameloader;

import voogasalad_GucciGames.gameData.GameDataInterface;
import voogasalad_GucciGames.gameData.GameDataLoader;
import voogasalad_GucciGames.gameData.GameInfo;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes.GameSceneInterface;

public class GameLoader {
    private GameControllerLoader myController;
    private GameDataInterface myData;

    public GameLoader(GameController controller) {
    	myData = new GameDataLoader();
        myController = controller;
        myController.loadGame(myData.loadGame("Duvall Tag"));
    }
}
