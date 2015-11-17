package voogasalad_GucciGames.testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MoveAction;
import voogasalad_GucciGames.gameEngine.mapObject.WhereToAttackAction;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class TestActions {
/*
	private MapObjectType test;
	private MapObject obj;
	private AllPlayers players;
	
	@Before
	public void setUp () {
		test = new MapObjectType("test","none");
		obj = new MapObject(test, new TargetCoordinateSingle(50,50), 0);
		List<GamePlayerPerson> listOfPlayers = new ArrayList<>();
		GamePlayerPerson player = new GamePlayerPerson(null,0);
		player.getMapObjects().add(obj);
		listOfPlayers.add(player);
		players = new AllPlayers(listOfPlayers);
	}

	@Test
	public void testMove(){
		TargetCoordinateSingle newCoordinate = new TargetCoordinateSingle(100,100);
		CommunicationParams params = new CommunicationParams(players,null,null,obj);
		obj.getObjectType().addAction("MoveAction",new MoveAction());
		obj.getObjectType().getAction("MoveAction").action(params, newCoordinate);
		assertEquals("Should be (100,100): ",obj.getCoordinate(),newCoordinate);
	}
	
	@Test
	public void testWhereMove(){
		CommunicationParams params = new CommunicationParams(players,null,null,obj);
		obj.getObjectType().addActionTarget("WhereToAttackAction",new WhereToAttackAction());
		ATargetCoordinate where = obj.getObjectType().getActionTarget("WhereToAttackAction").coordinatesToAct(params);
		TargetCoordinateMultiple multi = (TargetCoordinateMultiple) where;
		assertEquals("Should be (50,50): ",multi.getCoordinates().get(0), new TargetCoordinateSingle(50,50));
	}
	*/
}
