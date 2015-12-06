package voogasalad_GucciGames.gameplayer.gameloader;

import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GameInfoToGamePlayer;
import voogasalad_GucciGames.gameData.wrapper.GamePlayerSave;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;

public interface GameControllerLoader {

	public void loadGame(GameInfo game);
        public void loadGameSave(GamePlayerSave game);
		public GameInfoToGamePlayer getGame();
		public GameEngineToGamePlayerInterface getEngine();
	
}
