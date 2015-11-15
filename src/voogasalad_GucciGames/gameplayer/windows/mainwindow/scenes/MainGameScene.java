package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameplayer.controller.GameEngineInterface;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.GameWindowInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.GameKeyHandler;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.main.MainMap;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.leftbar.LeftBar;

public class MainGameScene extends GameScene implements GameSceneController{

	private GameEngineInterface myGame;
	private Scene myCurrentScene;
	
	private BorderPane myPane;
	
	private LeftBar myLeftBar;
	private MainMap myMap;
	
	private GameKeyHandler myKeyHandler;
	
	public MainGameScene(GameSceneManager manager, GameWindowInterface window, String config) {
		super(manager, window, config);
	}
	
	private void loadGameData(){
		// do a bunch of stuff with myLoader
		myGame = myManager.getLoader().getCurrentGame();
	}

	@Override
	public void load() {
		//STEPS UPON LOAD:
		/*
		 * 1. Load Game Data
		 * 2. Show Splash Screen
		 * 3. Show Main Menu / Level Chooser
		 * 4. Show Game Screen
		 * 5. Show Result of Game
		 * 6. Go to Next Level or Go to Main Menu
		 */
		
		initializePane();
		showGame();
		myWindow.loadScene(myScene);
		
	}
	
	private void initializePane(){
        myPane = new BorderPane();
        myScene = new Scene(myPane);
        myScene.getStylesheets().add(myConfig.getString("CssFile"));
	}
	
	private void showSplash(){
		//Text text = new Text(myGame.getName());
		
		
	}
	
	private void showGame(){
	    myLeftBar = new LeftBar(this, myGame, null, myConfig); 
	    myMap = new MainMap(this, myGame);
	    myPane.setCenter(myMap.getParent());
	    myPane.setLeft(myLeftBar.getParent());

	    myKeyHandler = new GameKeyHandler(this);
	    myScene.addEventFilter(KeyEvent.KEY_PRESSED, (e)->myKeyHandler.handle(e));
	    
	}

	@Override
	public MapInterface getMap() {
		return myMap;
	}
	
}
