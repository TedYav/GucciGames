package voogasalad_GucciGames.gameData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

public class GameDataManager implements GameDataInterface {

	private XStreamGameEngine myXStream;
	private GameListManager myGameList;

    private final ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GameData");

    
	public GameDataManager(){
		myXStream = new XStreamGameEngine();
		myGameList = new GameListManager();
	}

	@Override
	public GameEngine loadGame(String name) {
		return myXStream.loadGameByName(name);
	}

	@Override
	public List<String> getAvailableGames() {
		return myGameList.listGames();	
	}

	@Override
	public GameEngine loadGameFromFile(String path) throws GameDataException {
		if(!path.endsWith(myConfig.getString("GameExtension")))
			throw new GameDataException("Please select a valid game data file.)");
		return myXStream.loadGameInfo(path);
	}

	@Override
	public GameEngine loadDefault() {
		return myXStream.loadGameByName(myGameList.listGames().get(0));
	}
	
	public String getGamePath(GameEngine game){
		return myXStream.gameNameToFileName(game.getGameName());
	}

}
