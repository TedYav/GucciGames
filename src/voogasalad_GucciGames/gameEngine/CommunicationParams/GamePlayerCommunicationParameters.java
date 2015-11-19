package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;

public class GamePlayerCommunicationParameters extends CommunicationParameters{

	boolean isGameOver;
	Map<Integer, Integer> scoreOfEachPlayer;
	Map<Integer, EndGameConditions> stateOfEachPlayer;
	
}
