package voogasalad_GucciGames.gameEngine.objectActions;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerWealthChar;

public class BuildArcherEvent extends MapObjectEvent {

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		// TODO Auto-generated method stub
		AllPlayers players = params.getEngine().getPlayers();
		GamePlayerPerson player = players.getActivePlayer(params.getCalledMe().getPlayerID());
		PlayerWealthChar wealth = (PlayerWealthChar) player.getCharacteristics("PlayerWealthChar");
		if (wealth.getWealth() >= 50) {
			wealth.setWealth(wealth.getWealth() - 50);
		}
		return (new MapObjectBuilder()).build("Archer", params);
	}

	@Override
	protected GridCoordinateParameters executeRequest(BasicParameters params) {
		// TODO Auto-generated method stub
		return (new MapObjectBuilder()).request(params);
	}

}
