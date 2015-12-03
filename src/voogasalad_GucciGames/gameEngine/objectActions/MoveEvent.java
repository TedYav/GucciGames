package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.HashSet;
import java.util.Set;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRules.defaultRules.UnitsMovablePerTurn;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

//Test at testing.TestActions.java

public class MoveEvent extends MapObjectEvent{


	public MoveEvent(String actionName) {
		super(actionName);
		getRuleList().add(new UnitsMovablePerTurn());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		// TODO Auto-generated method stub
		System.out.println("Move Action");
		TargetCoordinateSingle target = params.getNewLocation();
		MapObject moving = params.getCalledMe();
		moving.setCoordinate(target);
		AllPlayers players = params.getEngine().getPlayers();
		GamePlayerPerson player = players.getActivePlayer(params.getCalledMe().getPlayerID());
		player.getMovable().updateMoves();
		//params.getMovePerson().updateMoves();
		
		ChangedParameters myParameters  = new ChangedParameters();
		
		myParameters.addUnit(moving);
		
		return myParameters;
	}

	@Override
	protected GridCoordinateParameters executeRequest(BasicParameters params) {
		// TODO Auto-generated method stub
		System.out.println("Move Request");
		AllPlayers players = params.getEngine().getPlayers();
		TargetCoordinateMultiple result = new TargetCoordinateMultiple();
		MapObject calledMe = params.getCalledMe();

		// getting the range
		MovableCharacteristic mc = (MovableCharacteristic) calledMe.getCharacteristic("MovableCharacteristic");
		double range = mc.getRange();
		// going through neutral player
		TargetCoordinateSingle caller = (TargetCoordinateSingle) calledMe.getCoordinate();
		Set<TargetCoordinateSingle> otherCoor = new HashSet<>();
		
		players.getNonNeutralMapObjects().stream().forEach(obj -> {
			otherCoor.add((TargetCoordinateSingle) obj.getCoordinate());
		});
		players.getActivePlayer(-1).getMapObjects().stream().forEach(mo -> {
			// Fix this
			if(mo.hasCharacteristic("TileCharacteristic") || mo.getName().equals("TileCharacteristic")){
				TargetCoordinateSingle single = (TargetCoordinateSingle) mo.getCoordinate();
				double dx = Math.abs(single.getCenterX()-caller.getCenterX());
				double dy = Math.abs(single.getCenterY()-caller.getCenterY());
				
				if (checkNeighborhood(dx,dy,range) && !otherCoor.contains(mo.getCoordinate())){
					result.addTargetCoordinateSingle(mo.getCoordinate());
				}
			}
		});
		return new GridCoordinateParameters(result);
	}

}
