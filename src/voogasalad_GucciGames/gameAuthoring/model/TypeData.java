package voogasalad_GucciGames.gameAuthoring.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ParamObjParser;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameAuthoring.model.factories.ActionFactory;
import voogasalad_GucciGames.gameAuthoring.model.factories.CharacteristicMapFactory;
import voogasalad_GucciGames.gameAuthoring.model.factories.GroovyActionFactory;
import voogasalad_GucciGames.gameAuthoring.model.factories.GroovyMapChars;
import voogasalad_GucciGames.gameAuthoring.model.factories.PlayerFactory;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

public class TypeData implements IGameProperties {
	private ObservableList<MapObjectType> tileTypes;
	private ObservableList<MapObjectType> unitTypes;
	private ObservableList<MapObjectType> structureTypes;

	private Map<Integer, GamePlayerPerson> mapOfPlayers;

	private Map<String, ActionParam> myActionParams = new HashMap<String, ActionParam>();
	private Map<String, ObjParam> myMapObjectCharParams = new HashMap<String, ObjParam>();
	private Map<String, ObjParam> myPlayerCharParams = new HashMap<String, ObjParam>();
	private Map<String, RuleParams> myRules = new HashMap<String, RuleParams>();
	private Map<String, ObjParam> myConditions = new HashMap<String, ObjParam>();
	private Map<String, ObjParam> myOutcomes = new HashMap<String, ObjParam>();

	private Map<String, GActionParams> myGroovyActionParams = new HashMap<String, GActionParams>();
	private Map<String, GCharParam> myGroovyMapObjectCharParams = new HashMap<String, GCharParam>();


	private CharacteristicMapFactory mapCharacteristicFactory = new CharacteristicMapFactory();
	private GroovyMapChars groovyMapCharacteristicFactory = new GroovyMapChars();
	private GroovyActionFactory groovyActionFactory = new GroovyActionFactory();
	private PlayerFactory playerCharacteristicFactory = new PlayerFactory();
	private ActionFactory actionFactory = new ActionFactory();

	public TypeData() {
		tileTypes = FXCollections.observableArrayList();
		unitTypes = FXCollections.observableArrayList();
		structureTypes = FXCollections.observableArrayList();
		ParamObjParser parser = new ParamObjParser();
		Set<ObjParam> mapObjCharacteristics = parser.getMapObjChars();
		for (ObjParam param: mapObjCharacteristics){
			myMapObjectCharParams.put(param.getName(), param);
		}    
		Set<ActionParam> actions = parser.getActions();
		for (ActionParam action: actions){
			myActionParams.put(action.getName(), action);
			System.out.println(action.getName());
		}
		Set<ObjParam> playerChar = parser.getPlayerChars();
		for (ObjParam characteristics: playerChar){
			myPlayerCharParams.put(characteristics.getName(), characteristics);
		}
		Set<ObjParam> conditions = parser.getConditions();
		for (ObjParam param: conditions){
			myConditions.put(param.getName(), param);
		}
		Set<ObjParam> outcomes = parser.getOutcomes();
		for (ObjParam param: outcomes){
			myOutcomes.put(param.getName(), param);
		}
		Set<RuleParams> rules = parser.getRules();
		for (RuleParams param: rules){
			myRules.put(param.getName(), param);
		}

		mapOfPlayers = new HashMap<>();
		// add neutral player
		mapOfPlayers.put(-1, new GamePlayerPerson(-1));


		tileTypes = FXCollections.observableArrayList();
		unitTypes = FXCollections.observableArrayList();
		structureTypes = FXCollections.observableArrayList();


		//		characteristicFactory = new CharacteristicFactory();
		//		MapObjectType type = new MapObjectType("student", "./", 0);
		//		ObjParamValue objParamVal = new ObjParamValue(type);
		//		objParamVal.setObjName("MovableCharacteristic" );
		//		Map<String, String> map = new HashMap<>();
		//		map.put("range", "1");
		//		map.put("maxNumOfMoves", "4");
		//		objParamVal.setParamValues(map);
		//		try {
		//			characteristicFactory.create(myMapObjectCharParams, objParamVal);
		//		} catch (NoSuchMethodException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (SecurityException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (ClassNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (InstantiationException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (IllegalAccessException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (IllegalArgumentException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (InvocationTargetException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

	}

	public void addTileType(MapObjectType type) {
		tileTypes.add(type);
	}
	public void addUnitType(MapObjectType type) {
		unitTypes.add(type);
	}
	public void addStructureType(MapObjectType type) {
		structureTypes.add(type);
	}
	public ObservableList<MapObjectType> getImmutableTileTypes() {
		return FXCollections.unmodifiableObservableList(tileTypes);
	}
	public ObservableList<MapObjectType> getImmutableUnitTypes() {
		return FXCollections.unmodifiableObservableList(unitTypes);
	}
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return FXCollections.unmodifiableObservableList(structureTypes);
	}

	@Override
	public List<ObjParam> getAllMapObjCharParams() {
		return new ArrayList<>(myMapObjectCharParams.values());
	}

	@Override
	public List<ObjParam> getSelectedMapObjCharParams(List<String> selectedChar) {
		return myMapObjectCharParams.values().stream()
				.filter(c -> selectedChar.contains(c.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<ObjParam> getAllPlayerCharParams() {
		return new ArrayList<>(myPlayerCharParams.values());
	}

	@Override
	public List<ObjParam> getSelectedPlayerCharParams(List<String> selectedChar) {
		return myPlayerCharParams.values().stream()
				.filter(c -> selectedChar.contains(c.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<ObjParam> getAllOutcomes() {
		return new ArrayList<>(myOutcomes.values());
	}

	@Override
	public List<ObjParam> getSelectedOutcomes(List<String> selectedOutcomes) {
		return myOutcomes.values().stream()
				.filter(c -> selectedOutcomes.contains(c.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<ObjParam> getAllConditions() {
		return new ArrayList<>(myConditions.values());
	}

	@Override
	public List<ObjParam> getSelectedConditions(List<String> selectedConditions) {
		return myConditions.values().stream()
				.filter(c -> selectedConditions.contains(c.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<RuleParams> getAllRules() {
		return new ArrayList<>(myRules.values());
	}


	@Override
	public List<RuleParams> getSelectedRules(List<String> selectedRules) {
		return myRules.values().stream()
				.filter(c -> selectedRules.contains(c.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public void addPlayerCharacteristic(int playerID, ObjParamValue param) {
		try {
			System.out.println(playerID + " HI");
			System.out.println(this.mapOfPlayers.size());
			System.out.println(mapOfPlayers.get(playerID)==null);
			System.out.println(param.getName());
			mapOfPlayers.get(playerID).addCharacterstic(param.getName(), (APlayerChars)playerCharacteristicFactory.create(myPlayerCharParams, param));
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.err.println("FAILED TO ADD CHARACTERISTIC");
		}

	}

	@Override
	public void addMapObjectCharacteristic(ObjParamValue param) {
		if (myGroovyMapObjectCharParams.containsKey(param.getName())) {
			try {
				param.getMapObjectType().addCharacteristic((AMapObjectCharacteristic)groovyMapCharacteristicFactory.create(myGroovyMapObjectCharParams, param));
			} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				System.err.println("FAILED TO ADD GROOVY CHARACTERISTIC");
			}

		}
		else {
			try {
				param.getMapObjectType().addCharacteristic((AMapObjectCharacteristic)mapCharacteristicFactory.create(myMapObjectCharParams, param));
			} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				System.err.println("FAILED TO ADD CHARACTERISTIC");
			}
		}

	}

	@Override
	public void addActionParamValue(ActionParamsValue param) {
		System.out.println("adding action in type data");
		if (myGroovyActionParams.containsKey(param.getName())) {
			try {
				param.getMapObjectType().addAction((MapObjectEvent)groovyActionFactory.create(myGroovyActionParams, param));
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				System.err.println("FAILED TO ADD GROOVY ACTION");
			}
			
			
		}
		else {
			try {
				param.getMapObjectType().addAction(actionFactory.createAction(myActionParams, param));
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				System.err.println("FAILED TO ADD ACTION");
			}
		}

	}

	@Override
	public List<ActionParam> getAllActions() {
		return new ArrayList<>(myActionParams.values());
	}

	public void changeOwner(MapObject mapObject, int playerID) {
		int oldID = mapObject.getPlayerID();
		mapOfPlayers.get(oldID).getMapObjects().remove(mapObject);
		mapObject.setOwnerID(playerID);
		mapOfPlayers.get(playerID).addMapObject(mapObject);
	}

	public Map<Integer, GamePlayerPerson> getMapOfPlayers() {
		return mapOfPlayers;
	}

	@Override
	public void addGroovyCharacteristic(GCharParam param) {
		myGroovyMapObjectCharParams.put(param.getName(), param);
		ObjParam charparam = new ObjParam(param.getName(), ObjType.MAP_CHAR, 0);
		param.getAllParams().keySet().stream().forEach(key -> {
			charparam.addParam(param.getAllParams().get(key), key);
		});
		myMapObjectCharParams.put(param.getName(), charparam);

	}

	@Override
	public void addGroovyAction(GActionParams param) {
		myGroovyActionParams.put(param.getName(), param);
		ActionParam actionParam = new ActionParam(param.getName());
		myActionParams.put(param.getName(), actionParam);
	}

	public void setNumberOfPlayers(int n) {
		for (int i = 0; i < n; i ++) {
			System.out.println("Adding to map!");
			mapOfPlayers.put(i, new GamePlayerPerson(i));
		}
	}

	public int getNumberOfPlayers() {
		return mapOfPlayers.size();
	}

}
