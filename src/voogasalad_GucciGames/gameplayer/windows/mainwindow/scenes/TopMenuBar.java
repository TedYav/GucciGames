package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.FileMenu;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.GameMenuBar;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.MenuBarElement;

public class TopMenuBar extends WindowComponent implements DisplayComponent{
	private HBox container;

	public TopMenuBar(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		container = new HBox();
	    GameMenuBar menu = new GameMenuBar();
	     container.getChildren().add(menu.getNodeToDraw());
	}

	@Override
	public Node getNodeToDraw() {
		return container;
	}

	@Override
	public Parent getParent() {
		return container;
	}

}
