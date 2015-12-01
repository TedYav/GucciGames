package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.eventhandler.MapKeyHandler;
import voogasalad_GucciGames.gameplayer.eventhandler.MapMouseHandler;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.GameWindowInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.LeftBar;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.RightBar;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.ActionDisplay;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayChat;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectDetails;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectImage;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.EndTurnButton;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.GameStatsDisplay;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.main.MainMap;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.FileItem;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.GameMenu;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.GameMenuBar;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.TopBar;

public class MainGameScene extends GameScene {

    private GameControllerInterface myController;
    private Scene myCurrentScene;

    private BorderPane myPane;

    private LeftBar myLeftBar;
    private List<DisplayComponent> leftComponents;
    private RightBar myRightBar;
    private List<DisplayComponent> rightComponents;
    private GameMenuBar myMenuBar;
    private MainMap myMap;

    private MapKeyHandler myKeyHandler;
    private ResourceBundle myCssBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.scenes.CssClasses");
	private TopBar myTopBar;

    public MainGameScene(GameSceneManager manager, GameWindowInterface window, String config) {
        super(manager, window, config);
    }

    private void loadGameData(){
        myController = myManager.getLoader().getController();
        myController.setScene(this);
    }

    @Override
    public void load() {
    	
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

        leftComponents=myController.getGame().getLeftComponents().stream().map(s->(DisplayComponent)Reflection.createInstance(s, myController)).collect(Collectors.toList());
        rightComponents=myController.getGame().getRightComponents().stream().map(s->(DisplayComponent)Reflection.createInstance(s, myController)).collect(Collectors.toList());

        myLeftBar = new LeftBar(this, myController,leftComponents);
        myPane.setLeft(myLeftBar.getParent());

        myRightBar = new RightBar(this, myController, rightComponents);
        myPane.setRight(myRightBar.getParent());

//	    FileItem file = new FileItem(null,myManager.getStage()); //TODO: create for properties file?
//	    List<GameMenu> listOfGameMenus = new ArrayList<GameMenu>();
//	    listOfGameMenus.add(file);
//	    myMenuBar = new GameMenuBar(listOfGameMenus);
//	    myPane.setTop(myMenuBar.returnToolbar());
	    myTopBar = new TopBar(this, myController);
	    myPane.setTop(myTopBar.getParent());

        enableObservers();

    }

    private void enableObservers() {
        myMap.addUnitListener(myLeftBar.requestListeners());
        myMap.addUnitListener(myRightBar.requestListeners());
    }

    @Override
    public void update () {
        myLeftBar.updateComponents();
        myRightBar.updateComponents();
    }

}
