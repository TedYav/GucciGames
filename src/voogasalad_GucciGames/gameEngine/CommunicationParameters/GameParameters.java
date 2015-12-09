package voogasalad_GucciGames.gameEngine.CommunicationParameters;

import java.util.Map;

import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;
import voogasalad_GucciGames.gameplayer.controller.GameResultInterface;

public class GameParameters extends CommunicationParameters implements GameParametersInterface{
    private double mapWidth;
    private double mapHeight;
    private String gameName;
   // private Map<String,Double> score;
    private int currentTurnPlayer;
    
//    private boolean turnEnded;

    private GameResult gameResult;

    public double getMapWidth () {
        return mapWidth;
    }
    public void setMapWidth (double mapWidth) {
        this.mapWidth = mapWidth;
    }
    public double getMapHeight () {
        return mapHeight;
    }
    public void setMapHeight (double mapHeight) {
        this.mapHeight = mapHeight;
    }
    public String getGameName () {
        return gameName;
    }
    public void setGameName (String gameName) {
        this.gameName = gameName;
    }
   /*
    @Override
	public Map<String,Double> getScore () {
        return score;
    }
    public void setScore (Map<String,Double> score) {
        this.score = score;
    }
    */
    public int getCurrentTurnPlayer () {
        return currentTurnPlayer;
    }
    public void setCurrentTurnPlayer (int currentTurnPlayer) {
        this.currentTurnPlayer = currentTurnPlayer;
    }

    public GameResult getGameResult () {
        return gameResult;
    }
    public void setGameResult (GameResult gameResult) {
        this.gameResult = gameResult;
    }
    @Override
    public int whoseTurn () {
        return getCurrentTurnPlayer();
    }
    @Override
    public GameResultInterface getResults () {
        return getGameResult();
    }


	public boolean isTurnEnded() {
		return turnEnded;
	}
	
    
}
