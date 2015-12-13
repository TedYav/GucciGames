package voogasalad_GucciGames.gameplayer.controller;

public interface GameParametersInterface {
	// public Map<String,Double> getScore();

	public int whoseTurn();

	public boolean isTurnEnded();

	public GameResultInterface getResults();
}
