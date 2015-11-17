package voogasalad_GucciGames.gameEngine.objectActions;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MainGameEngineCommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.WhereToParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

// Test at testing.TestActions.java

public class WhereToMoveEvent extends MapObjectEvent{



	public WhereToMoveEvent(String actionName) {
		super(actionName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CommunicationParams execute(CommunicationParams params) {
		// TODO Auto-generated method stub
		AllPlayers players = params.getPlayers();
		GamePlayerPerson player = players.getPlayerById(((MainGameEngineCommunicationParams) params).getTurn());
		TargetCoordinateMultiple result = new TargetCoordinateMultiple();
		MapObject calledMe = params.getCalledMe();

		//calledMe.getObjectType().getCharacteristic("Moveable").getRange();
		double range = 1;

		// going through neutral player
		players.getPlayerById(-1).getMapObjects().stream().forEach(mo -> {
			if(mo.getObjectType().getName().equals("Tile")){
				TargetCoordinateSingle single = (TargetCoordinateSingle) mo.getCoordinate();
				TargetCoordinateSingle caller = (TargetCoordinateSingle) calledMe.getCoordinate();
				double delta = Math.abs(single.getCenterX()-caller.getCenterX())+Math.abs(single.getCenterY()-caller.getCenterY());
				// check to see if can move
				if (delta <= range){
					result.addTargetCoodinateSingle(mo.getCoordinate());
				}
			}
		});
		
		return new WhereToParams(params,result);
	}


}
