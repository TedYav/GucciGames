package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.controller.GameEngineInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;

public class RightBar extends WindowComponent {
    private VBox container;
    private double spacing = 5;
    ResourceBundle myBundle;
	public RightBar(GameScene scene, GameEngineInterface game, ResourceBundle bundle) {
		super(scene, game);
        container = new VBox(spacing);
        myBundle=bundle;
        initializeData();
	}

	private void initializeData() {
        GameStatsDisplay gameStats = new GameStatsDisplay();
        
        container.getChildren().add(gameStats.getNodeToDraw());
        container.getStyleClass().add(myBundle.getString("RightVBox"));
	}

	@Override
	public Parent getParent() {
		return container;
	}


}
