package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al
 *
 */
public class UnitsMovablePerTurn extends Rules {


	public UnitsMovablePerTurn() {

	}

	@Override
	public Boolean executeRules(CommunicationParams communicationParams, int playerID) {
		GamePlayerPerson player = communicationParams.getPlayers().getPlayerById(playerID);
		int objAllowedMovs = player.getUnitsMoved();
		int playerAllowedMoves = player.getAllowedMovesPerTurn();
		int objcurrentMoves = 0; // fix it;
		int playerCurrentMoves = player.getTurnCounter();
		if (playerAllowedMoves == -1 && objAllowedMovs == -1) {
			return true;
		} else if (playerAllowedMoves != -1 && objAllowedMovs == -1) {
			return evalIf(playerAllowedMoves, playerCurrentMoves);
		} else if (playerAllowedMoves == -1 && objAllowedMovs != -1) {
			return evalIf(objAllowedMovs, objcurrentMoves);
		} else if (playerAllowedMoves != -1 && objAllowedMovs != -1) {
			if (objAllowedMovs < playerAllowedMoves) {
				return evalIf(objAllowedMovs, objcurrentMoves);

			} else if (playerAllowedMoves < objAllowedMovs) {
				return evalIf(playerAllowedMoves, playerCurrentMoves);

			} else {
				if ((playerCurrentMoves < playerAllowedMoves) && (objcurrentMoves < objAllowedMovs)) {
					return true;
				} else
					return false;

			}

		}

		return false;
	}

	private Boolean evalIf(int allowed, int current) {
		if (current < allowed) {
			return true;
		} else {
			return false;
		}
	}
}
