package voogasalad_GucciGames.gameplayer.controller;

import java.util.Map;

public interface GameParametersInterface {
    //public Map<String,Double> getScore();

    public int whoseTurn();

    public boolean isTurnEnded();

    public GameResultInterface getResults();
}
