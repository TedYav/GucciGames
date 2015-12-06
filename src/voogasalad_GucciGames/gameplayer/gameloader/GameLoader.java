package voogasalad_GucciGames.gameplayer.gameloader;

import java.util.List;

import voogasalad_GucciGames.gameData.GameDataException;
import voogasalad_GucciGames.gameData.GameDataInterface;
import voogasalad_GucciGames.gameData.GameDataManager;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GamePlayerSave;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.exceptions.ErrorHandler;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneInterface;

public class GameLoader {
    private GameControllerLoader myController;
    private GameDataInterface myData;
    private boolean gameLoaded = false;
    private String mySelectedGame = "";

    public GameLoader(GameController controller) {
    	myData = new GameDataManager();
        myController = controller;
    }
    
    public List<String> getAvailableGames(){
    	return myData.getAvailableGames();
    }
    
    public List<String> getAvailableSaves(String gameName){
        return myData.getAvailableSaves(gameName);
    }
    
    public void loadGame(String gameName){
        myController.loadGame(myData.loadGame(gameName));
        gameLoaded = true;
    }
    public void loadGameSave(String saveName){
        myController.loadGameSave(myData.loadSave(saveName));
        gameLoaded = true;
    }
    public void saveGame(GamePlayerSave game) {
        myData.saveGame(game);
    }
    
    public void loadGameFromFile(String path){
    	try {
			myController.loadGame(myData.loadGameFromFile(path));
		} catch (GameDataException e) {
			ErrorHandler.handleError(e);
			gameLoaded = false;
			openGameBrowser();
		}
    	gameLoaded = true;
    }
    
    public boolean gameLoaded(){
    	return gameLoaded;
    }
    
    public void openGameBrowser(){
    	System.err.println("NOT IMPLEMENTED YET SORRY");
    }
    
    public void selectGame(String gameName){
    	mySelectedGame = gameName;
    }
    
    public void loadSelectedGame() throws GameDataException{
    	if(!mySelectedGame.isEmpty()){
    		loadGame(mySelectedGame);
    		mySelectedGame = "";
    	}
    	else{
    		throw new GameDataException("No game selected to load");
    	}
    }
    public void loadSelectedGameSave() throws GameDataException{
        if(!mySelectedGame.isEmpty()){
                loadGameSave(mySelectedGame);
                mySelectedGame = "";
        }
        else{
                throw new GameDataException("No game selected to load");
        }
    }

	public void loadDefault() {
		myController.loadGame(myData.loadDefault());
	}
}
