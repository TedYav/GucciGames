package voogasalad_GucciGames.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GuiData;
import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.TileCharacteristic;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.CheckOnePlayerLeft;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.EndLevel;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerMovesPerTurn;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;
import voogasalad_GucciGames.gameEngine.gameRules.defaultRules.PlayersActivePerTurn;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.objectActions.AttackEvent;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEventHandler;
import voogasalad_GucciGames.gameEngine.objectActions.MoveEvent;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.windows.GameWindowManager;

public class DemoMaker extends Application{

	private static String defaultGameLocation = "./src/games/demo.xml";

	@Override
	public void start(Stage stage) throws Exception {
		XStreamGameEngine xStream = new XStreamGameEngine();
		System.out.println("Creating and saving engine.");
		xStream.saveGameInfo(createGame());
		GameWindowManager windowmanager = new GameWindowManager("Duvall Tag");
	}

	public static void main(String[] args){
		launch(args);
	}
	private static GameInfo createGame() {
		GameLevelEngine level1 = makeLevel(4,4);
		level1.setMyChoosability(true);
//		level1.setName("Easy");
		GameLevelEngine level2 = makeLevel(8, 8);
                level2.setMyChoosability(true);
//                level1.setName("Easy");
		GameLevelEngine level3 = makeLevel(20,20);
                level3.setMyChoosability(true);
//                level1.setName("Easy");

		GameInfo game = new GameInfo("Duvall Tag");
                game.getGameEngine().setLevel("Easy", level1);
                game.getGameEngine().setLevel("Medium", level2);
                game.getGameEngine().setLevel("Hard", level3);

                GuiData gui = new GuiData();
                game.setGuiData(gui);
		return game;
	}

	private static GameLevelEngine makeLevel(int width, int height) {
		Map<Integer,GamePlayerPerson> myMapOfPlayers = new TreeMap<Integer,GamePlayerPerson>();
		myMapOfPlayers.put(-1,new GamePlayerPerson(-1)); //neutral player
		myMapOfPlayers.put(0,new GamePlayerPerson(0)); //player 1

		myMapOfPlayers.put(1,new GamePlayerPerson(1)); //player 2

		MapObject soldier = new MapObject(new TargetCoordinateSingle(1,0),0,1,"Duvall", "units/duvall.png");
		//MapObjectType archer = new MapObjectType("Student" , "player/images/smile.png");
		//MapObjectType ground = new MapObjectType("TileCharacteristics", "player/images/dummytexture.jpg");
		//MapObjectType water = new MapObjectType("TileCharacteristics", "player/images/dummytexture2.jpg");

		//MapObject soldier1 = new MapObject(soldier,new TargetCoordinateSingle(1,0),0,1);


		myMapOfPlayers.get(-1).setMovable(new PlayerMovesPerTurn(2));
		myMapOfPlayers.get(0).setMovable(new PlayerMovesPerTurn(2));
		myMapOfPlayers.get(1).setMovable(new PlayerMovesPerTurn(2));

		MovableCharacteristic myMovableCharacteristic = new MovableCharacteristic(1, 3);
		HealthCharacteristic myHealthCharacteristic = new HealthCharacteristic(5);
                PlayersActivePerTurn moveOwn = new PlayersActivePerTurn();
        TileCharacteristic myTileCharacteristic = new TileCharacteristic(false);
                List<Rules> moveRules = new ArrayList<Rules>();
                moveRules.add(moveOwn);
		MoveEvent myMoveEvent = new MoveEvent("Move",moveRules,new ArrayList<Outcome>());
		soldier.addEvent("Move", myMoveEvent);

		Conditions onePlayerLeft = new CheckOnePlayerLeft();
		List<Conditions> endGameConditions = new ArrayList<Conditions>();
		endGameConditions.add(onePlayerLeft);

		Outcome endGame = new EndLevel(endGameConditions, "other", "nextLevel", "Hard");
		List<Outcome> attackOutcomes = new ArrayList<Outcome>();
		attackOutcomes.add(endGame);
		List<Rules> attackRules = new ArrayList<Rules>();
		attackRules.add(moveOwn);
		AttackEvent myAttackEvent = new AttackEvent("Attack",attackRules,attackOutcomes);

		AttackCharacteristic myAttackCharacteristic = new AttackCharacteristic(3, 100, 2);


		soldier.addCharacteristic("MovableCharacteristic", myMovableCharacteristic);
		soldier.addCharacteristic("HealthCharacteristic", myHealthCharacteristic);
		soldier.addCharacteristic("AttackCharacteristic", myAttackCharacteristic);

		soldier.addEvent("Attack", myAttackEvent);

		for (int i=0;i<width;i++) {
			for (int j=0;j<height;j++) {
				MapObject newObj;
				if((i+j)%2==0){
					newObj = new MapObject(new TargetCoordinateSingle(i,j),-1,0,"Water", "tiles/water.jpg");
				}
				else{
					newObj = new MapObject(new TargetCoordinateSingle(i,j),-1,0,"Grass", "tiles/grass.jpg");
				}
				newObj.addCharacteristic("TileCharacteristic", myTileCharacteristic);
				newObj.setOwnerID(-1);
				myMapOfPlayers.get(-1).getMapObjects().add(newObj);
				if ((i+j)%9==0) {
					MapObject arch = new MapObject(new TargetCoordinateSingle(i,j),1,1,"Student" , "units/smile.png");
					arch.addCharacteristic("HealthCharacteristic", new HealthCharacteristic(10));
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

		GameLevelEngine engine = new GameLevelEngine(myPlayers);
		engine.setMapHeight(height);
		engine.setMapWidth(width);
		for(Integer key: myMapOfPlayers.keySet()){
			myMapOfPlayers.get(key).getMapObjects().stream().forEach(mo -> {
				mo.setMapObjectEventHandler(new MapObjectEventHandler(new BasicParameters(null,engine)));;
			});
		}
		return engine;
	}

}
