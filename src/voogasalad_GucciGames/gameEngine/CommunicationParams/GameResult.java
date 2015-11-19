package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.Map;
import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;

public class GameResult {
    private Map<Integer,EndGameConditions> playerConditions;
    private Map<String,Integer> finalScores;
    private int winner;
    public Map<Integer,EndGameConditions> getPlayerConditions () {
        return playerConditions;
    }
    public void setPlayerConditions (Map<Integer,EndGameConditions> playerConditions) {
        this.playerConditions = playerConditions;
    }
    public Map<String,Integer> getFinalScores () {
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
