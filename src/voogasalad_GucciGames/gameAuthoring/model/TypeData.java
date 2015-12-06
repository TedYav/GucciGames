package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ParamObjParser;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameAuthoring.model.factories.CharacteristicFactory;

public class TypeData implements IGameProperties {
	private ObservableList<MapObjectType> tileTypes;
	private ObservableList<MapObjectType> unitTypes;
	private ObservableList<MapObjectType> structureTypes;
	
	private Map<String, ActionParam> myActionParams = new HashMap<String, ActionParam>();
	private Map<String, ObjParam> myMapObjectCharParams = new HashMap<String, ObjParam>();
	private Map<String, RuleParams> myRules = new HashMap<String, RuleParams>();
	private Map<String, ObjParam> myConditions = new HashMap<String, ObjParam>();
	private Map<String, ObjParam> myOutcomes = new HashMap<String, ObjParam>();
	private Map<String, ObjParam> myPlayerCharParams = new HashMap<String, ObjParam>();
	
	private CharacteristicFactory characteristicFactory = new CharacteristicFactory();

	public TypeData() {
    	ParamObjParser parser = new ParamObjParser();
    	Set<ObjParam> mapObjCharacteristics = parser.getMapObjChars();
    	for (ObjParam param: mapObjCharacteristics){
    		myMapObjectCharParams.put(param.getName(), param);
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
    	Set<ActionParam> actions = parser.getActions();
    	for (ActionParam action: actions){
    		myActionParams.put(action.getName(), action);
    		System.out.println(action.getName());
    	}
    	Set<ObjParam> playerChar = parser.getPlayerChars();
    	for (ObjParam characteristics: playerChar){
    		myPlayerCharParams.put(characteristics.getName(), characteristics);
    	}
    	
		
		tileTypes = FXCollections.observableArrayList();
	
		MapObjectType objType = new MapObjectType("Tile1", "tiles/water.jpg", 0);
		MapObjectType objType2 = new MapObjectType("Tile2", "tiles/sand.jpg", 0);

		tileTypes.add(objType);
		tileTypes.add(objType2);
		
		unitTypes = FXCollections.observableArrayList();
		
		structureTypes = FXCollections.observableArrayList();
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
	public void addCharacteristic(ObjParam param, MapObjectType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RuleParams> getAllRules() {
		// TODO Auto-generated method stub
		return new ArrayList<>(myRules.values());
	}

	@Override
	public List<RuleParams> getSelectedRules(List<String> selectedRules) {
		// TODO Auto-generated method stub
		return myRules.values().stream()
				.filter(c -> selectedRules.contains(c.getName()))
				.collect(Collectors.toList());	
	}

	@Override
	public void addActionParamValue(ActionParamsValue param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCharParamValue(ObjParamValue param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActionParam> getAllActions() {
		return new ArrayList<>(myActionParams.values());
	}


	


}
