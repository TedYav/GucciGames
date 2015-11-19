package voogasalad_GucciGames.gameEngine.objectActions;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MainGameEngineCommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.WhereToParams;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

// Test at testing.TestActions.java

public class WhereToMoveEvent extends MapObjectEvent{

	public WhereToMoveEvent(String actionName) {
		super(actionName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CommunicationParameters execute(CommunicationParameters params) {
		// TODO Auto-generated method stub
		BasicParameters basic = (BasicParameters) params;
		AllPlayers players = basic.getPlayers();
		GamePlayerPerson player = players.getPlayerById(((MainGameEngineCommunicationParameters) params).getTurn());
		TargetCoordinateMultiple result = new TargetCoordinateMultiple();
		MapObject calledMe = basic.getCalledMe();

		// getting the range
		MovableCharacteristic mc = (MovableCharacteristic) calledMe.getObjectType().getCharacteristic("MovableCharacteristic");
		double range = mc.getRange();

		// going through neutral player
		TargetCoordinateSingle caller = (TargetCoordinateSingle) calledMe.getCoordinate();
		players.getPlayerById(-1).getMapObjects().stream().forEach(mo -> {
			if(mo.getObjectType().getName().equals("TileCharacteristics")){
				TargetCoordinateSingle single = (TargetCoordinateSingle) mo.getCoordinate();
				double delta = Math.abs(single.getCenterX()-caller.getCenterX())+Math.abs(single.getCenterY()-caller.getCenterY());
				// check to see if can move
				if (delta <= range){
					result.addTargetCoodinateSingle(mo.getCoordinate());
				}
			}
		});

		return new WhereToParams(result);
	}


}
