package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.player;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionParams;
import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;
import voogasalad_GucciGames.gameEngine.gameConditions.oucomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerUnitCondition extends PlayerConditions {

	public PlayerUnitCondition(ConditionParams condParams, BasicParameters params) {
		super(condParams, params);

	}

	@Override
	protected void apply(GamePlayerPerson player, BasicParameters params) {
		System.out.println("condition description: remove player with units = 0");
		System.out.println(player == null);
		if(player != null){
		int myID = player.getMyPlayerId();
		Outcome outcome = new Outcome(params.getPlayers());
		if (player.getMapObjects() != null) {
			if (player.getMapObjects().size() == 0) {
				player.setStatus(EndGameConditions.LOSE.toString());
				outcome.removePlayer(myID);//this needs to move to another map
				myParams.addRemoveIDs(myID);
			}
		}
	}
	}
}
