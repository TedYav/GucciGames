package voogasalad_GucciGames.gameplayer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import voogasalad.util.cloud.Cloud;
import voogasalad_GucciGames.datastructures.Coordinate;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GameInfoToGamePlayer;
import voogasalad_GucciGames.gameData.wrapper.GamePlayerSave;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.gameloader.GameControllerLoader;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindowManager;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.helpers.ResourceManager;

public class GameController implements GameControllerInterface, GameControllerAdvancedInterface, GameControllerLoader,
		GameControllerEngineInterface {

	private GameWindowManager myManager;
	private GameSceneManager mySceneManager;

	private GameEngineToGamePlayerInterface myCurrentEngine;
	private MapInterface myMap;
	private ResourceManager myResourceManager;
	private PlayerMapObjectInterface myTargetUnit;
	private GameInfoToGamePlayer myGame;

	// TODO: factor into component
	private String myActionInProgress;
	private PlayerMapObjectInterface activeMapObject;
	private List<Observer> activeMOObservers;
	private List<TargetCoordinateSingle> possibleMoves;
	private List<Observer> chatObservers;
	private GameParametersInterface endLevelParams;

	private GameLoader myLoader;

	private Cloud myCloud;

	public GameController(GameWindowManager manager) {
		myManager = manager;
		myResourceManager = new ResourceManager();
		myActionInProgress = "";
		activeMOObservers = new ArrayList<Observer>();
		possibleMoves = new ArrayList<TargetCoordinateSingle>();
		myLoader = new GameLoader(this);
		chatObservers = new ArrayList<Observer>();
	}

	@Override
	public void loadGame(GameInfo game) {
		// System.out.println("LOADGAME-GAMEINFO");
		loadGame(game, "1");
	}

	private void loadGame(GameInfoToGamePlayer game, String level) {
		myGame = game;
		myCurrentEngine = myGame.getEngineInterface();
		loadLevel(level);
	}

	@Override
	public void loadGameSave(GamePlayerSave game) {
		// System.out.println("LOADGAMESAVE");
		loadGame(game.getInfo(), game.getCurrentLevel());
	}

	public void loadLevel(String levelID) {
		if (myGame.getLevels().containsKey(levelID)) {
			// System.out.println("level changed");
			myCurrentEngine.setController(this);
			myCurrentEngine.changeCurrentLevel(levelID);
			// myCurrentLevelEngine =
			// myGame.getLevels().get(levelID).getGameEngine();
		}
	}

	@Override
	public List<TargetCoordinateSingle> setActionInProgress(String action, PlayerMapObjectInterface unit) {
		myActionInProgress = action;
		myTargetUnit = unit;
		//
		possibleMoves = myTargetUnit.performRequest(action).getListOfCoordinates();

		return possibleMoves;
	}

	@Override
	public String getActionInProgress() {
		return myActionInProgress;
	}

	@Override
	public void cancelAction() {
		myActionInProgress = "";
		myTargetUnit = null;
	}

	@Override
	public void performActionInProgress(Point2D target) {
		ChangedParameters params;// = myEngine.performAction(myActionInProgress,
									// activeMapObject,
									// Coordinate.PointToCoordinate(target));
		// cancelAction();

		//// SORRY FOR THE TIME BEING

		for (TargetCoordinateSingle coord : possibleMoves) {
			if (target.getX() == coord.getCenterX() && target.getY() == coord.getCenterY()) {
				params = activeMapObject.performAction(myActionInProgress, Coordinate.PointToCoordinate(target));
				cancelAction();
				List<PlayerMapObjectInterface> result;

				result = params.getChangedUnits();

				myMap.update(result);
				myManager.refresh();
				break;
			}
		}
		// workaround for canceling action by clicking outside of action range
		// (increments action i think?)
		cancelAction();
		myMap.update(new ArrayList<PlayerMapObjectInterface>());
		myManager.refresh();
	}

	@Override
	public boolean actionInProgress() {
		return !myActionInProgress.equals("");
	}

	@Override
	public MapInterface getMap() {
		return myMap;
	}

	@Override
	public void setMap(MapInterface map) {
		myMap = map;
	}

	@Override
	public List<PlayerMapObjectInterface> getInitialState() {
		return myCurrentEngine.getInitialState();
	}

	@Override
	public void endTurn() {
		// TODO Auto-generated method stub
		GameParametersInterface params = myCurrentEngine.endTurn();
		myManager.refresh();
		myCurrentEngine.getCurrentLevel().getLevelName();
		if (myCurrentEngine.getCurrentLevel().hasLevelEnded()) {
			endLevelParams = params;
			mySceneManager.sceneFinished();
		}
	}

	@Override
	public ResourceManager getResource() {
		return myResourceManager;
	}

	@Override
	public void setActiveMapObject(PlayerMapObjectInterface mapObj) {
		activeMapObject = mapObj;
		notifyMOObservers();
	}

	@Override
	public PlayerMapObjectInterface getActiveMapObject() {
		return activeMapObject;
	}

	@Override
	public void addActiveMOObserver(Observer o) {
		activeMOObservers.add(o);
	}

	private void notifyMOObservers() {
		for (Observer o : activeMOObservers) {
			o.update(null, activeMapObject);
		}
	}

	@Override
	public void addChatObserver(Observer o) {
		chatObservers.add(o);
	}

	private void notifyChatObservers(String chatLine) {
		for (Observer o : chatObservers) {
			o.update(null, chatLine);
		}
	}

	@Override
	public GameEngineToGamePlayerInterface getEngine() {
		// TODO Auto-generated method stub
		return myCurrentEngine;
	}

	@Override
	public GameInfoToGamePlayer getGame() {
		// System.out.println("GETGAME");
		return myGame;
	}

	@Override
	public GameLoader getLoader() {
		return myLoader;
	}

	@Override
	public void loadDefaultLevel() {
		String defaultLevel = myGame.getLevels().keySet().stream().findFirst().get();
		myCurrentEngine.changeCurrentLevel(defaultLevel);

	}

	public void refreshGUI() {
		// TODO Auto-generated method stub
		// System.out.println("refresh? PLEASE");

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update/Query the FX classes here
				mySceneManager.loadScene("MainGameScene");

			}
		});
		// mySceneManager.loadScene("MainGameScene");

	}

	/**
	 * @param mySceneManager
	 */
	public void setSceneManager(GameSceneManager sceneManager) {
		mySceneManager = sceneManager;
	}

	@Override
	public void updateChat(String string) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update/Query the FX classes here

				notifyChatObservers(string);

			}
		});

	}

	@Override
	public void sendMessage(String s) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update/Query the FX classes here

				myCurrentEngine.sendMessage(s);

			}
		});

	}

	public void loadNextLevel() {
		// TODO Auto-generated method stub

	}

	@Override
	public GameParametersInterface getEndLevelParams() {
		return endLevelParams;
	}

	@Override
	public void uploadScore(String playername, double score) {
		if (myCloud == null) {
			myCloud = new Cloud();
		}
		myCloud.addHighScore(myGame.getGameName(), playername, score);
	}

}
