package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;


public class RightBar extends WindowComponent implements Observer {
	private PlayerMapObjectInterface activeMapObject;
    private VBox container;
    private double spacing = 5;
    ResourceBundle myBundle;
    private GameControllerInterface myController;
    
    private GameStatsDisplay gameStats;
    
    private ResourceBundle myCssBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.scenes.CssClasses");
	public RightBar(GameScene scene, GameControllerInterface controller, ResourceBundle bundle) {
		super(scene, controller);
        container = new VBox(spacing);
        myBundle=bundle;
        myController=controller;
        initializeData();
        
	}

	private void initializeData() {
		myController.addMOObserver(this);
        gameStats = new GameStatsDisplay(myController);
        ActionDisplay actions = new ActionDisplay(myController);
        container.getChildren().add(actions.getNodeToDraw());
        container.getChildren().add(gameStats.getNodeToDraw());
        container.getStyleClass().add(myCssBundle.getString("RightVBox"));
        container.setPrefWidth(Double.parseDouble(myCssBundle.getString("rightprefwidth")));
               
        Button endTurn = new Button("End turn");
        endTurn.setOnMouseClicked(e->{
            System.out.println(myController.getEngine());
            myController.endTurn();
            });
        container.getChildren().add(endTurn);
	}
	

	@Override
	public Parent getParent() {
		return container;
	}

	@Override
	public void update(Observable o, Object arg) {
        if (arg!=null && arg.getClass().equals(PlayerMapObjectInterface.class)) {
        	activeMapObject = (PlayerMapObjectInterface)arg;
        }
       
        }
        public void updateStats() {
            gameStats.update();
	}

}
