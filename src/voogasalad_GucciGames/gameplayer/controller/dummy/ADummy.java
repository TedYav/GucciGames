package voogasalad_GucciGames.gameplayer.controller.dummy;

import java.util.List;

import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public abstract class ADummy {

	public abstract List<PlayerMapObjectInterface> performAction(String action, ATargetCoordinate target);
	
}
