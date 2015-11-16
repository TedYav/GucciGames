package voogasalad_GucciGames.gameplayer.windows.mainwindow;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes.GameSceneController;

public class GameKeyHandler implements EventHandler<KeyEvent> {

	private GameControllerInterface myController;
	
	public GameKeyHandler(GameControllerInterface controller){
		myController = controller;
	}
	
	@Override
	public void handle(KeyEvent e) {
		switch (e.getCode()) {
		case RIGHT:
		case LEFT:
		case UP:
		case DOWN:
			myController.getMap().move(e.getCode());
			break;
		default:
			System.out.println(e.getCode());
		}
	}


}
