package voogasalad_GucciGames.gameplayer.controller.dummy;

import java.util.List;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;

public abstract class ADummy {

	public abstract List<PlayerMapObjectInterface> performAction(String action, ATargetCoordinate target);

}
