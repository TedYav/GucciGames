package voogasalad_GucciGames.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import voogasalad_GucciGames.helpers.ResourceManager;

public class DemoMaker extends Application {

	private static String defaultGameLocation = "./src/games/demo.xml";

	private static ResourceManager resourceManager = new ResourceManager("Duvall Tag");
	
	@Override
	public void start(Stage stage) throws Exception {
		XStreamGameEngine xStream = new XStreamGameEngine();
		System.out.println("Creating and saving engine.");
		xStream.saveGameInfo(createGame());
		GameWindowManager windowmanager = new GameWindowManager("Duvall Tag");
	}

	public static void main(String[] args) {
		launch(args);
	}
	private static GameInfo createGame() {
		GameLevelEngine level1 = makeLevel(4, 4, 1);
		level1.setMyChoosability(true);
		// level1.setName("Easy");
		GameLevelEngine level2 = makeLevel(8, 8, 1);
		level2.setMyChoosability(true);
		// level1.setName("Easy");
		GameLevelEngine level3 = makeLevel(20, 20, 1);
		level3.setMyChoosability(true);
		// level1.setName("Easy");
		GameLevelEngine level4 = makeLevel(40, 40, 20);
		level4.setMyChoosability(true);

		resourceManager.copyImageToGame("tiles/lava.jpg");
		resourceManager.copyImageToGame("tiles/water.jpg");
		resourceManager.copyImageToGame("tiles/grass.jpg");
		resourceManager.copyImageToGame("units/duvall.jpg");
		resourceManager.copyImageToGame("units/smile.jpg");

		
		GameInfo game = new GameInfo("Duvall Tag");
		game.getGameEngine().addLevel("Easy", level1);
		game.getGameEngine().addLevel("Medium", level2);
		game.getGameEngine().addLevel("Hard", level3);
		//game.getGameEngine().addLevel("Ultra", level4);

		GuiData gui = new GuiData();
		game.setGuiData(gui);
		return game;
	}

	private static GameLevelEngine makeLevel(int width, int height, int numDuvalls) {
		Map<Integer, GamePlayerPerson> myMapOfPlayers = new TreeMap<Integer, GamePlayerPerson>();
		myMapOfPlayers.put(-1, new GamePlayerPerson(-1)); // neutral player
		myMapOfPlayers.put(0, new GamePlayerPerson(0)); // player 1

		myMapOfPlayers.put(1, new GamePlayerPerson(1)); // player 2

		// MapObjectType archer = new MapObjectType("Student" ,
		// "player/images/smile.png");
		// MapObjectType ground = new MapObjectType("TileCharacteristics",
		// "player/images/dummytexture.jpg");
		// MapObjectType water = new MapObjectType("TileCharacteristics",
		// "player/images/dummytexture2.jpg");

		// MapObject soldier1 = new MapObject(soldier,new
		// TargetCoordinateSingle(1,0),0,1);

		myMapOfPlayers.get(-1).setMovable(new PlayerMovesPerTurn(2));
		myMapOfPlayers.get(0).setMovable(new PlayerMovesPerTurn(2));
		myMapOfPlayers.get(1).setMovable(new PlayerMovesPerTurn(2));

		PlayersActivePerTurn moveOwn = new PlayersActivePerTurn();
		TileCharacteristic myTileCharacteristic = new TileCharacteristic(false);
		List<Rules> moveRules = new ArrayList<Rules>();
		moveRules.add(moveOwn);

		MoveEvent myMoveEvent = new MoveEvent("Move", moveRules, new ArrayList<Outcome>());


		Conditions onePlayerLeft = new CheckOnePlayerLeft();
		List<Conditions> endGameConditions = new ArrayList<Conditions>();
		endGameConditions.add(onePlayerLeft);

		Outcome endGame = new EndLevel("other", "Hard");
		for (Conditions cond : endGameConditions) {
			endGame.addCondition(cond);
		}
		List<Outcome> attackOutcomes = new ArrayList<Outcome>();
		attackOutcomes.add(endGame);
		List<Rules> attackRules = new ArrayList<Rules>();
		attackRules.add(moveOwn);
		AttackEvent myAttackEvent = new AttackEvent("Attack", attackRules, attackOutcomes);

		MapObject soldier = makeSoldier(myMoveEvent, myAttackEvent, 1, 0);
		myMapOfPlayers.get(0).getMapObjects().add(soldier);
		numDuvalls--;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				MapObject newObj;
				if(numDuvalls == 0){
				if ((i + j) % 2 == 0) {
					newObj = new MapObject(new TargetCoordinateSingle(i, j), -1, 0, "Water", "tiles/water.jpg");
				} else {
					newObj = new MapObject(new TargetCoordinateSingle(i, j), -1, 0, "Grass", "tiles/grass.jpg");
				}}
				else{
					newObj = new MapObject(new TargetCoordinateSingle(i, j), -1, 0, "Lava", "tiles/lava.jpg");
				}
				newObj.addCharacteristic("TileCharacteristic", myTileCharacteristic);
				newObj.setOwnerID(-1);
				myMapOfPlayers.get(-1).getMapObjects().add(newObj);
				
				if(numDuvalls > 0 && !((i + j) % 9 == 0)){
					Random rand = new Random();
					if(rand.nextInt(width*height) < numDuvalls){
						MapObject littlesoldier = makeSoldier(myMoveEvent, myAttackEvent, i, j);
						myMapOfPlayers.get(0).getMapObjects().add(littlesoldier);
						numDuvalls--;
					}
				}
				
				if ((i + j) % 9 == 0) {
					MapObject arch = makeArcher(myMoveEvent, myAttackEvent, i, j);

					myMapOfPlayers.get(1).getMapObjects().add(arch);
				}
			}
		}


		AllPlayers myPlayers = new AllPlayers(myMapOfPlayers);

		GameLevelEngine engine = new GameLevelEngine(myPlayers);
		engine.setMapHeight(height);
		engine.setMapWidth(width);
		for (Integer key : myMapOfPlayers.keySet()) {
			myMapOfPlayers.get(key).getMapObjects().stream().forEach(mo -> {
				mo.setMapObjectEventHandler(new MapObjectEventHandler(new BasicParameters(null, engine)));
				;
			});
		}
		return engine;
	}

	private static MapObject makeArcher(MoveEvent myMoveEvent, AttackEvent myAttackEvent, int i, int j) {
		MapObject arch = new MapObject(new TargetCoordinateSingle(i, j), 1, 1, "Student",
				"units/smile.png");
		arch.addCharacteristic("HealthCharacteristic", new HealthCharacteristic(10));
		arch.addCharacteristic("AttackCharacteristic", new AttackCharacteristic(3, 5, 2));
		arch.addCharacteristic("MovableCharacteristic", new MovableCharacteristic(5, 1));
		arch.addEvent("Move", myMoveEvent);

		arch.addEvent("Attack", myAttackEvent);
		return arch;
	}

	private static MapObject makeSoldier(MoveEvent moveEvent, AttackEvent attackEvent, int i, int j) {
		
		MovableCharacteristic myMovableCharacteristic = new MovableCharacteristic(1, 3);
		HealthCharacteristic myHealthCharacteristic = new HealthCharacteristic(5);
		MapObject soldier = new MapObject(new TargetCoordinateSingle(i, j), 0, 1, "Duvall", "units/duvall.png");

		soldier.addEvent("Move", moveEvent);
		
		AttackCharacteristic myAttackCharacteristic = new AttackCharacteristic(3, 100, 2);

		soldier.addCharacteristic("MovableCharacteristic", myMovableCharacteristic);
		soldier.addCharacteristic("HealthCharacteristic", myHealthCharacteristic);
		soldier.addCharacteristic("AttackCharacteristic", myAttackCharacteristic);

		soldier.addEvent("Attack", attackEvent);
		return soldier;
	}

}