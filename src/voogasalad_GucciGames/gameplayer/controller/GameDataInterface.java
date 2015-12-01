package voogasalad_GucciGames.gameplayer.controller;

import voogasalad_GucciGames.gameData.GameInfo;

public interface GameDataInterface {

	public void loadGames();

	GameInfo loadGameByName(String name);

}
