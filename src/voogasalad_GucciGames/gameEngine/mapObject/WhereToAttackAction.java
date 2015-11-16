package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.WhereToAttackParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;

public class WhereToAttackAction implements IMapObjectActionTarget{



	@Override
	public ATargetCoordinate coordinatesToAct(CommunicationParams communication) {
		// TODO Auto-generated method stub
		
		AllPlayers players = communication.getPlayers();
		GamePlayerPerson player = players.getActivePlayer(players.getCurrentTurn());
		List<ATargetCoordinate> result = new ArrayList<>();
		for(int i = 0; i < players.getNumberOfPlayers(); i++){
			for(MapObject mo: players.getActivePlayer(i).getMapObjects()){
				//you should not be able to attack yourself (in a normal sane game) (you might want to in other games)
				result.add(mo.getCoordinate());
			}
		}
		
		
		return new TargetCoordinateMultiple(result);
	}
	
	

}
