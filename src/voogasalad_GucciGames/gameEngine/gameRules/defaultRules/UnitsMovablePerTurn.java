package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al
 *
 */
public class UnitsMovablePerTurn extends Rules {
	private int playerID;
	private int playerMoves;
	private String objectType;
	private int objMoves;
	private String target;
	private CommunicationParams myCommunicationParams;


	/**this rule requires four parameter:
	 * 1. id of player the rule affects... if id = -1 or the id of player then the rule applies
	 * 2. targetperplayer = number of moves allowed per player.
	 * 3. mapObjType ... if = any or mytype then rule ok
	 * 4. targetperunit = the number of moves allowed by unit as long as they are less than playertarget
	 * getcurrentturn give activeplayerid
	 *
	 */

	public UnitsMovablePerTurn(CommunicationParams communicationParams) {
		super(communicationParams);
		//wait for joy and tina to push
		myCommunicationParams = communicationParams;
	}

	@Override
	public Boolean executeRules(int playerID) {

		int unitsMovedCounter = myCommunicationParams.getPlayers().getActivePlayer(playerID).getUnitsMoved();
		if (unitsMovedCounter < objMoves) {
			return true;
		} else
			return false;
	}

}
