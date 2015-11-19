package voogasalad_GucciGames.gameplayer.controller;

import java.util.Map;
import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;

public interface GameResultInterface {
    public Map<Integer,EndGameConditions> getEachPlayersCondition();
    
    public Map<String,Double> getFinalScore();
    
    public int whoWon();
}
