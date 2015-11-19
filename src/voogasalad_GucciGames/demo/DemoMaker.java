package voogasalad_GucciGames.demo;

import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.RealHealthCharacteristic;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.MovablePlayerCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameEngine.objectActions.AttackEvent;
import voogasalad_GucciGames.gameEngine.objectActions.MoveEvent;
import voogasalad_GucciGames.gameEngine.objectActions.WhereToAttackEvent;
import voogasalad_GucciGames.gameEngine.objectActions.WhereToMoveEvent;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.windows.GameWindowManager;

public class DemoMaker extends Application{

	private static String defaultEngineLocation = "./src/voogasalad_GucciGames/gameData/engine.xml";

	@Override
	public void start(Stage stage) throws Exception {
		XStreamGameEngine xStream = new XStreamGameEngine();
        System.out.println("Creating and saving engine.");
        xStream.saveEngine(createEngine(), defaultEngineLocation);
        xStream.loadEngine(defaultEngineLocation);

		GameWindowManager windowmanager = new GameWindowManager();
	}

    public static void main(String[] args){
        launch(args);
    }

    private static MainGameEngine createEngine() {
        Map<Integer,GamePlayerPerson> myMapOfPlayers = new TreeMap<Integer,GamePlayerPerson>();     
        myMapOfPlayers.put(-1,new GamePlayerPerson(-1)); //neutral player
        myMapOfPlayers.put(0,new GamePlayerPerson(0)); //player 1 

        myMapOfPlayers.put(1,new GamePlayerPerson(1)); //player 2
        
        MapObjectType soldier = new MapObjectType("Duvall", "player/images/duvall.png");
        MapObjectType archer = new MapObjectType("Student" , "player/images/smile.png");
        MapObjectType ground = new MapObjectType("TileCharacteristics", "player/images/dummytexture.jpg");
        MapObjectType water = new MapObjectType("TileCharacteristics", "player/images/dummytexture2.jpg");

        MapObject soldier1 = new MapObject(soldier,new TargetCoordinateSingle(1,0),0,1);
        
        
        myMapOfPlayers.get(-1).setMyMovable(new MovablePlayerCharacteristic(2));
        myMapOfPlayers.get(0).setMyMovable(new MovablePlayerCharacteristic(2));
        myMapOfPlayers.get(1).setMyMovable(new MovablePlayerCharacteristic(2));
        
		MovableCharacteristic myMovableCharacteristic = new MovableCharacteristic(1, 3);
		HealthCharacteristic myHealthCharacteristic = new RealHealthCharacteristic(5);

		MoveEvent myMoveEvent = new MoveEvent("Move");
		WhereToMoveEvent myMoveLocationEvent = new WhereToMoveEvent("WhereToMove");
		soldier.addAction("Move", myMoveEvent);
		soldier.addRequest("WhereToMove", myMoveLocationEvent);

		AttackEvent myAttackEvent = new AttackEvent("Attack");
		WhereToAttackEvent myAttackLocationEvent = new WhereToAttackEvent("WhereToAttack");
		AttackCharacteristic myAttackCharacteristic = new AttackCharacteristic(3, 100, 2);
		
		
		soldier1.addCharacteristic("MovableCharacteristic", myMovableCharacteristic);
		soldier1.addCharacteristic("HealthCharacteristic", myHealthCharacteristic);
		soldier1.addCharacteristic("AttackCharacteristic", myAttackCharacteristic);

		soldier.addAction("Attack", myAttackEvent);
		soldier.addRequest("WhereToAttack", myAttackLocationEvent);


        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
            	MapObject newObj = new MapObject(((i+j)%2==0)?water:ground, new TargetCoordinateSingle(i,j),-1,0);
            	myMapOfPlayers.get(-1).getMapObjects().add(newObj);
                if ((i+j)%3==0) {
                    MapObject arch = new MapObject(archer,new TargetCoordinateSingle(i,j),1,1);
                    arch.addCharacteristic("HealthCharacteristic", new RealHealthCharacteristic(10));
                    arch.addCharacteristic("AttackCharacteristic", new AttackCharacteristic(3,5,2));
                    arch.addCharacteristic("MovableCharacteristic", new MovableCharacteristic(5,1));
                    archer.addAction("Move", myMoveEvent);
                    archer.addRequest("WhereToMove", myMoveLocationEvent);
                    
                    archer.addAction("Attack", myAttackEvent);
                    archer.addRequest("WhereToAttack", myAttackLocationEvent);
                    
                    myMapOfPlayers.get(1).getMapObjects().add(arch);
                }
            }
        }

        myMapOfPlayers.get(0).getMapObjects().add(soldier1);

        AllPlayers myPlayers = new AllPlayers(myMapOfPlayers);

        MainGameEngine engine = new MainGameEngine(myPlayers);
        return engine;
    }

}
