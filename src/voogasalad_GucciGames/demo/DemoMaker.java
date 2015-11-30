package voogasalad_GucciGames.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameData.GameInfo;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.RealHealthCharacteristic;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.MovablePlayerCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.objectActions.AttackEvent;
import voogasalad_GucciGames.gameEngine.objectActions.MoveEvent;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.windows.GameWindowManager;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class DemoMaker extends Application{

	private static String defaultGameLocation = "./src/voogasalad_GucciGames/gameData/demogame.xml";

	@Override
	public void start(Stage stage) throws Exception {
		XStreamGameEngine xStream = new XStreamGameEngine();
		System.out.println("Creating and saving engine.");
		xStream.saveGameInfo(createGame(), defaultGameLocation);
		//xStream.loadEngine(defaultEngineLocation);
		GameWindowManager windowmanager = new GameWindowManager();
	}

	public static void main(String[] args){
		launch(args);
	}

	private static GameInfo createGame() {
		Map<Integer,GamePlayerPerson> myMapOfPlayers = new TreeMap<Integer,GamePlayerPerson>();     
		myMapOfPlayers.put(-1,new GamePlayerPerson(-1)); //neutral player
		myMapOfPlayers.put(0,new GamePlayerPerson(0)); //player 1 

		myMapOfPlayers.put(1,new GamePlayerPerson(1)); //player 2

		MapObject soldier = new MapObject(new TargetCoordinateSingle(1,0),0,1,"Duvall", "player/images/duvall.png");
		//MapObjectType archer = new MapObjectType("Student" , "player/images/smile.png");
		//MapObjectType ground = new MapObjectType("TileCharacteristics", "player/images/dummytexture.jpg");
		//MapObjectType water = new MapObjectType("TileCharacteristics", "player/images/dummytexture2.jpg");

		//MapObject soldier1 = new MapObject(soldier,new TargetCoordinateSingle(1,0),0,1);


		myMapOfPlayers.get(-1).setMovable(new MovablePlayerCharacteristic(2));
		myMapOfPlayers.get(0).setMovable(new MovablePlayerCharacteristic(2));
		myMapOfPlayers.get(1).setMovable(new MovablePlayerCharacteristic(2));

		MovableCharacteristic myMovableCharacteristic = new MovableCharacteristic(1, 3);
		HealthCharacteristic myHealthCharacteristic = new RealHealthCharacteristic(5);

		MoveEvent myMoveEvent = new MoveEvent("Move");
		soldier.addEvent("Move", myMoveEvent);

		AttackEvent myAttackEvent = new AttackEvent("Attack");
		AttackCharacteristic myAttackCharacteristic = new AttackCharacteristic(3, 100, 2);


		soldier.addCharacteristic("MovableCharacteristic", myMovableCharacteristic);
		soldier.addCharacteristic("HealthCharacteristic", myHealthCharacteristic);
		soldier.addCharacteristic("AttackCharacteristic", myAttackCharacteristic);

		soldier.addEvent("Attack", myAttackEvent);

		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				MapObject newObj;
				if((i+j)%2==0){
					newObj = new MapObject(new TargetCoordinateSingle(i,j),-1,0,"TileCharacteristic", "player/images/dummytexture2.jpg");
				}
				else{
					newObj = new MapObject(new TargetCoordinateSingle(i,j),-1,0,"TileCharacteristic", "player/images/dummytexture.jpg");
				}
				myMapOfPlayers.get(-1).getMapObjects().add(newObj);
				if ((i+j)%9==0) {
					MapObject arch = new MapObject(new TargetCoordinateSingle(i,j),1,1,"Student" , "player/images/smile.png");
					arch.addCharacteristic("HealthCharacteristic", new RealHealthCharacteristic(10));
					arch.addCharacteristic("AttackCharacteristic", new AttackCharacteristic(3,5,2));
					arch.addCharacteristic("MovableCharacteristic", new MovableCharacteristic(5,1));
					arch.addEvent("Move", myMoveEvent);

					arch.addEvent("Attack", myAttackEvent);

					myMapOfPlayers.get(1).getMapObjects().add(arch);
				}
			}
		}

		myMapOfPlayers.get(0).getMapObjects().add(soldier);

		AllPlayers myPlayers = new AllPlayers(myMapOfPlayers);

		MainGameEngine engine = new MainGameEngine(myPlayers);
		for(Integer key: myMapOfPlayers.keySet()){
			myMapOfPlayers.get(key).getMapObjects().stream().forEach(mo -> {
				mo.setBasicParameters(new BasicParameters(null,engine));
			});
		}
	        List<String> leftComponents=new ArrayList<String>();
	        List<String> rightComponents=new ArrayList<String>();
	        leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectImage");
	        leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectDetails");
	        leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayChat");
	        rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.ActionDisplay");
	        rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.GameStatsDisplay");
	        rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.EndTurnButton");
		GameInfo game = new GameInfo(engine,leftComponents,rightComponents);
		return game;
	}

}
