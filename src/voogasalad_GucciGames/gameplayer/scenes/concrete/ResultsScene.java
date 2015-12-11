package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.scene.input.KeyEvent;
import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerScore;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindowInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.GameResultsScreen;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.LevelResultsScreen;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.ResultsScreen;

public class ResultsScene extends GameScene{

	private ResultsScreen myResultsScreen;
	
	private Map<String, String> myInfoMap;
		
	public ResultsScene(GameSceneManager manager, GameWindowInterface window, String config) {
		super(manager, window, config);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		buildInfoMap();
		if(gameOver()){
			myResultsScreen = new GameResultsScreen(this, myManager.getController(), myConfig.getString("EndGameTitle"), myInfoMap);
		}else{
			myResultsScreen = new LevelResultsScreen(this, myManager.getController(), myConfig.getString("EndLevelTitle"), myInfoMap);
		}
		loadParent(myResultsScreen.getParent());
		getScene().addEventFilter(KeyEvent.ANY, e -> done());
	}

	private void done() {
		if(gameOver()){
			myManager.loadScene("MainMenuScene");
		}
		else{
			myManager.getController().getEngine().changeCurrentLevel(myManager.getController().getEngine().getCurrentLevel().getNextLevel());
			myManager.sceneFinished();
		}
	}

	private void buildInfoMap() {
		myInfoMap = new LinkedHashMap<>();
		GameParametersInterface results = myManager.getController().getEndLevelParams();
		results.getResults().getEachPlayerConditions().keySet().forEach( s -> addPlayerResults(s));
	}

	private void addPlayerResults(Integer id) {
		double score = ((PlayerScore) myManager.getController().getEngine().getPlayerCharacteristic("PlayerScore", id)).getScore();
		myInfoMap.put("Player " + id, ((Double)score).toString() + " " + playerState(id));
		if(gameOver()){
			myManager.getController().uploadScore("Player" + id, score);
		}
	}

	private String playerState(Integer s) {
		return gameOver() ? getOutcomeText(myManager.getController().getEndLevelParams().getResults().getEachPlayerConditions().get(s)) : "";
	}

	private String getOutcomeText(EndGameConditions endGameConditions) {
		switch(endGameConditions){
		case WIN:
			return myConfig.getString("WinText");
		case DRAW:
			return myConfig.getString("DrawText");
		case LOSE:
			return myConfig.getString("LoseText");
		default:
			return "";
		}
	}

	private boolean gameOver() {
		return myManager.getController().getEngine().getCurrentLevel().getNextLevel() == null;
	}

}
