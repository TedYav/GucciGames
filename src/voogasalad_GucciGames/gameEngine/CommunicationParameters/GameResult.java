package voogasalad_GucciGames.gameEngine.CommunicationParameters;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;
import voogasalad_GucciGames.gameplayer.controller.GameResultInterface;

public class GameResult implements GameResultInterface{
    private Map<Integer,EndGameConditions> playerConditions;
    private Map<String,Integer> finalScores;
    private int winner;
    public Map<Integer,EndGameConditions> getEachPlayerConditions () {
        return playerConditions;
    }
    public void setPlayerConditions (Map<Integer,EndGameConditions> playerConditions) {
        this.playerConditions = playerConditions;
    }
    public Map<String,Integer> getPlayerScores () {
        return finalScores;
    }
    public void setFinalScores (Map<String,Integer> finalScores) {
        this.finalScores = finalScores;
    }
    public int whoWon () {
        return winner;
    }
    public void setWinner (int winner) {
        this.winner = winner;
    }
}
