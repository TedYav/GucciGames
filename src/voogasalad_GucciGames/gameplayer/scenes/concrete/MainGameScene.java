package voogasalad_GucciGames.gameplayer.scenes.concrete;

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
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.GameWindowInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.BottomBar;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.LeftBar;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MainMenuOverlay;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.OverlayComponent;
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

    private BorderPane myPane;

    private LeftBar myLeftBar;
    private List<DisplayComponent> leftComponents;
    private RightBar myRightBar;
    private List<DisplayComponent> rightComponents;
    private BottomBar myBottomBar;
    private List<DisplayComponent> bottomComponents;
    private GameMenuBar myMenuBar;
    private MainMap myMap;
    private OverlayComponent myOverlayMenu;

    private TopBar myTopBar;

    public MainGameScene(GameSceneManager manager, GameWindowInterface window, String config) {
        super(manager, window, config);
    }

    private void loadGameData(){
        myController = myManager.getController();
    }

    @Override
    public void load() {
    	
        initializePane();
        loadGameData();
        showGame();
        loadParent(myPane);
        initializeOverlays();

    }

    private void initializeOverlays() {
    	myOverlayMenu = new OverlayComponent(this, myController, new MainMenuOverlay(this, myController));
	}

	private void initializePane(){
        myPane = new BorderPane();
    }

    private void showGame(){

        myMap = new MainMap(this, myController);
        myPane.setCenter(myMap.getParent());

        leftComponents=myController.getGame().getGuiComponents(myGuiNames.getString("Components").split(",")[0]).stream().map(s->(DisplayComponent)Reflection.createInstance(s, this, myController)).collect(Collectors.toList());
        rightComponents=myController.getGame().getGuiComponents(myGuiNames.getString("Components").split(",")[1]).stream().map(s->(DisplayComponent)Reflection.createInstance(s, this, myController)).collect(Collectors.toList());
        bottomComponents=myController.getGame().getGuiComponents(myGuiNames.getString("Components").split(",")[2]).stream().map(s->(DisplayComponent)Reflection.createInstance(s, this, myController)).collect(Collectors.toList());

        myLeftBar = new LeftBar(this, myController,leftComponents);
        myPane.setLeft(myLeftBar.getParent());

        myRightBar = new RightBar(this, myController, rightComponents);
        myPane.setRight(myRightBar.getParent());
        
        
        // forgive me father for I have sinned, I did not want to edit his code
        myBottomBar = new BottomBar(this,myController, bottomComponents);
        myPane.setTop(myBottomBar.getParent());

//	    FileItem file = new FileItem(null,myManager.getStage()); //TODO: create for properties file?
//	    List<GameMenu> listOfGameMenus = new ArrayList<GameMenu>();
//	    listOfGameMenus.add(file);
//	    myMenuBar = new GameMenuBar(listOfGameMenus);
//	    myPane.setTop(myMenuBar.returnToolbar());
//	    myTopBar = new TopBar(this, myController);
//	    myPane.setTop(myTopBar.getParent());

        enableObservers();

    }

    private void enableObservers() {
        myMap.addUnitListener(myLeftBar.requestListeners());
        myMap.addUnitListener(myRightBar.requestListeners());
        myMap.addUnitListener(myBottomBar.requestListeners());
    }

    @Override
    public void refresh () {
        myLeftBar.updateComponents();
        myRightBar.updateComponents();
        myBottomBar.updateComponents();
    }

}
