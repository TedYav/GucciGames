package voogasalad_GucciGames.gameplayer.gameloader;

import java.util.List;

import voogasalad_GucciGames.gameData.GameDataException;
import voogasalad_GucciGames.gameData.GameDataInterface;
import voogasalad_GucciGames.gameData.GameDataLoader;
import voogasalad_GucciGames.gameData.GameInfo;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.exceptions.ErrorHandler;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes.GameSceneInterface;

public class GameLoader {
    private GameControllerLoader myController;
    private GameDataInterface myData;

    public GameLoader(GameController controller) {
    	myData = new GameDataLoader();
        myController = controller;
    }
    
    public List<String> getAvailableGames(){
    	return myData.getAvailableGames();
    }
    
    public void loadGame(String gameName){
    	myController.loadGame(myData.loadGame(gameName));
    }
    
    public void loadGameFromFile(String path){
    	try {
			myController.loadGame(myData.loadGameFromFile(path));
		} catch (GameDataException e) {
			ErrorHandler.handleError(e);
			openGameBrowser();
		}
    }
    
    public void openGameBrowser(){
    	System.err.println("NOT IMPLEMENTED YET SORRY");
    }
}
