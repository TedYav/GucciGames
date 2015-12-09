package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad.util.cloud.Cloud;
import voogasalad.util.cloud.data.GameScore;
import voogasalad_GucciGames.gameEngine.IGameLevelToGamePlayer;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class HighScoresScene extends GameMenuScene {
	
	public HighScoresScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
	}

	@Override
	protected Map<String, MenuAction> buildOptionMap() {
		
		Map<String, MenuAction> options = new LinkedHashMap<>();
		Cloud myCloud = new Cloud();
		List<GameScore> scores = myCloud.retrieveHighScores(myManager.getController().getGame().getGameName());
		int numScores = 0;
		if(scores.size() > 0){
			for(GameScore s : scores){
				options.put(String.format(myConfig.getString("ScoreFormat"), s.getScore(), s.getPlayerName(), s.getTitle()), null);
				numScores++;
				if(numScores >= Integer.parseInt(myConfig.getString("NumScores")))
					break;
			}
		}
		else{
			options.put("No high scores yet :(", null);
		}
		options.put("Back", () -> myManager.loadScene(myConfig.getString("PrevScene")));
		return options;
	}
	
	@Override
	public void load(){
		super.load();
		myMenu.setTitle(String.format(myConfig.getString("MenuTitle"), myManager.getController().getGame().getGameName()));
	}

	private void loadLevel(IGameLevelToGamePlayer level) {
	    System.out.println("loading"+level.getLevelName());
		getManager().getController().loadLevel(level.getLevelName());
		myManager.sceneFinished();
	}
}
