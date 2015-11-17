package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.eventhandler.MapKeyHandler;
import voogasalad_GucciGames.gameplayer.eventhandler.MapMouseHandler;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.GameWindowInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar.LeftBar;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar.RightBar;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.main.MainMap;

public class MainGameScene extends GameScene {

	private GameControllerInterface myController;
	private Scene myCurrentScene;
	
	private BorderPane myPane;
	
	private LeftBar myLeftBar;
	private RightBar myRightBar;
	private MainMap myMap;
	
	private MapKeyHandler myKeyHandler;
	
	public MainGameScene(GameSceneManager manager, GameWindowInterface window, String config) {
		super(manager, window, config);
	}
	
	private void loadGameData(){
		myController = myManager.getLoader().getController();
		myController.setScene(this);
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
		loadGameData();
		showGame();
		myWindow.loadScene(myScene);
		
	}
	
	private void initializePane(){
        myPane = new BorderPane();
        myScene = new Scene(myPane);
        myScene.getStylesheets().add(myConfig.getString("CssFile"));
	}
	
	private void showSplash(){
		
	}
	
	private void showGame(){
		
	    myMap = new MainMap(this, myController);
	    myPane.setCenter(myMap.getParent());
	
	    myLeftBar = new LeftBar(this, myController, myConfig);
	    myPane.setLeft(myLeftBar.getParent());
	    
	    myRightBar = new RightBar(this, myController, myConfig);
	    myPane.setRight(myRightBar.getParent());

	    enableObservers();
	       
	}
	
	private void enableObservers() {
		
	}
	
}
