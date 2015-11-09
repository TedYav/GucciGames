package voogasalad_GucciGames.gameauthor;
import java.util.List;

import voogasalad_GucciGames.gamedata.games.GameFile;

/**
 * Links the game data service to the game authoring environment.
 * 
 * TODO: think up a better name for these interfaces
 * @author Ted Yavuzkurt
 *
 */
public interface GameDataToGameAuthoringEnvironment {
	
	public List<GameFile> loadGameList();

}
