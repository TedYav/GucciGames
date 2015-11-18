package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al
 *
 */
public class UnitsMovablePerTurn extends Rules {

	@Override
	public Boolean executeRules(BasicParameters communicationParams, int playerID) {
		GamePlayerPerson player = communicationParams.getPlayers().getPlayerById(playerID);
		int objAllowedMoves = player.getUnitsMoved();
		int playerAllowedMoves = player.getAllowedMovesPerTurn();
		int objcurrentMoves = 0; // fix it;
		int playerCurrentMoves = player.getTurnCounter();
		if (playerAllowedMoves == -1 && objAllowedMoves == -1) {
			return true;
		} else if (playerAllowedMoves != -1 && objAllowedMoves == -1) {
			return evalIf(playerAllowedMoves, playerCurrentMoves);
		} else if (playerAllowedMoves == -1 && objAllowedMoves != -1) {
			return evalIf(objAllowedMoves, objcurrentMoves);
		} else if (playerAllowedMoves != -1 && objAllowedMoves != -1) {
			if (objAllowedMoves < playerAllowedMoves) {
				return evalIf(objAllowedMoves, objcurrentMoves);

			} else if (playerAllowedMoves < objAllowedMoves) {
				return evalIf(playerAllowedMoves, playerCurrentMoves);

			} else {
				if ((playerCurrentMoves < playerAllowedMoves) && (objcurrentMoves < objAllowedMoves)) {
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
