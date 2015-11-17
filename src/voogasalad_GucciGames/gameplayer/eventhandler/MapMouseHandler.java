package voogasalad_GucciGames.gameplayer.eventhandler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public class MapMouseHandler extends GameEventHandler implements EventHandler<MouseEvent> {

	private MapCellInterface myCell;
	
	public MapMouseHandler(GameControllerInterface controller, MapCellInterface cell) {
		super(controller);
		myCell = cell;
	}

	@Override
	public void handle(MouseEvent e) {		
	}
	
	

}
