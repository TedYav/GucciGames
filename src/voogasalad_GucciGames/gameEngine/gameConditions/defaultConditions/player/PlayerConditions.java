package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.player;

import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.DefaultConditions;
import voogasalad_GucciGames.gameEngine.gameConditions.oucomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class PlayerConditions extends DefaultConditions {
	private List<GamePlayerPerson> myPlayerList;
	public PlayerConditions(List<GamePlayerPerson> playerList, Outcome outcome) {
		super(playerList, outcome);
		myPlayerList=playerList;
	}

	@Override
	public void execute() {
		Iterator<GamePlayerPerson> playersIterator = myPlayerList.iterator();
		while(playersIterator.hasNext()){
			apply(playersIterator.next());
		}
	}
	protected abstract void apply(GamePlayerPerson player);
}
