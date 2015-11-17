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
	public RightBar(GameScene scene, GameControllerInterface controller, ResourceBundle bundle) {
		super(scene, controller);
        container = new VBox(spacing);
        myBundle=bundle;
        initializeData();
	}

	private void initializeData() {
		myController.addMOObserver(this);
        GameStatsDisplay gameStats = new GameStatsDisplay();
        ActionDisplay actions = new ActionDisplay(myController);
        container.getChildren().add(actions.getNodeToDraw());
        container.getChildren().add(gameStats.getNodeToDraw());
        container.getStyleClass().add(myBundle.getString("RightVBox"));
        
        Button endTurn = new Button("End turn");
        endTurn.setOnMouseClicked(e->{
            System.out.println("end");
            myController.getEngine().endTurn();
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

}
