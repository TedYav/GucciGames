package voogasalad_GucciGames.gamedata;

import java.util.List;

import voogasalad_GucciGames.gameauthor.GameDataToGameAuthoringEnvironment;
import voogasalad_GucciGames.gamedata.games.GameFile;
import voogasalad_GucciGames.gameplayer.controller.GameDataToGamePlayer;

public class GameData implements GameDataToGamePlayer, GameDataToGameAuthoringEnvironment {

	/*
	 * Local File Interface functions
	 */
	
	/**
	 * Returns list of games available to load
	 */
	public List<GameFile> loadGameList(){
		return null;
	}
	
	/**
	 * 
	 * Loads the given game into the supplied Game Engine.
	 * 
	 * @param game
	 * 		:Game to load
	 * @param engine
	 * 		:GameEngine to send objects to
	 */
	public void loadGame(GameFile game, GameEngineToGameData engine) throws GameDataException {
		
	}
	
	/**
	 * 
	 * @param game GameFile object to be written, contains game name and ID
	 * @param engine Game engine where to write the gameto read data from
	 * @param path 
	 * @throws GameDataException
	 */
	public void saveGame(GameFile game,GameEngineToGameData engine, String path) throws GameDataException {
		
	}

	/*
	 * Cloud interface functions
	 */
	
}
