package voogasalad_GucciGames.gameplayer.eventhandler;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class GameInputHandler {

	private GameControllerInterface myController;
	private MapKeyHandler myMapKeyHandler;
	
	public GameInputHandler(GameControllerInterface controller){
		myController = controller;
		myMapKeyHandler = new MapKeyHandler(myController);
	}
	
	public EventHandler<KeyEvent> mapKeyHandler(){
		return myMapKeyHandler;
	}
	
}
