package voogasalad_GucciGames.gameplayer.eventhandler;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class MapKeyHandler extends GameEventHandler implements EventHandler<KeyEvent> {
	
	public MapKeyHandler(GameControllerInterface controller){
		super(controller);
	}
	
	@Override
	public void handle(KeyEvent e) {
		switch (e.getCode()) {
		case RIGHT:
		case LEFT:
		case UP:
		case DOWN:
//			myController.getMap().move(e.getCode());
			break;
		default:
//			System.out.println(e.getCode());
		}
	}


}
