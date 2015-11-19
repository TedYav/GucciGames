package voogasalad_GucciGames.gameplayer.controller;

import java.util.Map;

public interface GameParamsInterface {
    public Map<String,Double> getScore();
    
    public int whoseTurn();
    
    public boolean gameWon();
    
    public GameResultInterface getResults();
}
