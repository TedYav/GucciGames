package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import java.util.ArrayList;
import java.util.List;
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
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.FileMenu;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.GameMenu;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.GameMenuBar;

public class MainGameScene extends GameScene {

	private GameControllerInterface myController;
	private Scene myCurrentScene;
	
	private BorderPane myPane;
	
	private LeftBar myLeftBar;
	private RightBar myRightBar;
	private GameMenuBar myMenuBar;
	private MainMap myMap;
	
	private MapKeyHandler myKeyHandler;
        private ResourceBundle myCssBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.scenes.CssClasses");
	
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
        myScene.getStylesheets().add(myCssBundle.getString("CssFile"));
	}
	
	private void showSplash(){
		
	}
	
	private void showGame(){
		


	    myMap = new MainMap(this, myController);
	    myPane.setCenter(myMap.getParent());
	    System.out.println(myPane.getCenter().getBoundsInParent());
	
	    myLeftBar = new LeftBar(this, myController, myConfig);
	    myPane.setLeft(myLeftBar.getParent());
	    
	    myRightBar = new RightBar(this, myController, myConfig);
	    myPane.setRight(myRightBar.getParent());
	    
	    FileMenu file = new FileMenu(null,myManager.getStage()); //TODO: create for properties file?
	    List<GameMenu> listOfGameMenus = new ArrayList<GameMenu>();
	    listOfGameMenus.add(file);
	    myMenuBar = new GameMenuBar(listOfGameMenus);
	    myPane.setTop(myMenuBar.returnToolbar());

	    enableObservers();
	       
	}
	
	private void enableObservers() {
		myMap.addUnitListener(myLeftBar.requestListener());
	}
	
}
