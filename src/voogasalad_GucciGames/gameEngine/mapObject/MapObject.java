package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.objectActions.IGamePlayerMapObjectAction;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEventHandler;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class MapObject implements PlayerMapObjectInterface {

	/*
	 * Check if: unit: .hasCharacteristic("UnitCharacteristic"); health:
	 * .hasCharacteristic("HealthCharacteristic"); tile:
	 * .hasCharactersitic("TileCharacteristic"); etc.
	 */

	private ATargetCoordinate myCoordinate;
	private int myOwnerID;
	private int myLayer;
	private String myName;
	private String myImagePath;
	private Map<String, AMapObjectCharacteristic> myCharacteristics;
	private MapObjectEventHandler myEventHandler;
	private Map<String, MapObjectEvent> myEvents; //test
	
	public MapObject(ATargetCoordinate coor, int id, int layer, 
			String name, String imagePath){

		this.myCoordinate = coor;
		this.myOwnerID = id;
		this.myLayer = layer;
		this.myName = name;
		this.myImagePath = imagePath;

		this.myCharacteristics = new TreeMap<>();
		this.myEvents = new HashMap<>();
	}

	public MapObject(String name, String imagePath){
		this(null,-1,0,name,imagePath);
	}

	public MapObject(MapObject obj, ATargetCoordinate coor, int id) {
		this(obj, coor, id, 0);
	}

	public MapObject(MapObject obj, ATargetCoordinate coor, int id, int layer){
		this(coor,id,layer,obj.getName(),obj.getImagePath());
	}

	public MapObject(ATargetCoordinate coor, int ownerID) {
		this(coor, ownerID, 0, "", "");
	}

	public MapObject(ATargetCoordinate coor, int ownerID, int layer) {
		this(coor, ownerID, layer, "", "");
	}

	@Override
	public AMapObjectCharacteristic getCharacteristic(String name) {
		return this.myCharacteristics.get(name);
	}

	public void addCharacteristic(String name, AMapObjectCharacteristic characteristic) {
		this.myCharacteristics.put(name, characteristic);
	}

	public boolean hasCharacteristic(String name) {
		return this.myCharacteristics.containsKey(name);
	}

	public List<String> getCharacteristicStrings() {
		return new ArrayList<String>(this.myCharacteristics.keySet());
	}

	@Override
	public ATargetCoordinate getCoordinate() {
		return myCoordinate;
	}

	public void setCoordinate(ATargetCoordinate coordinate) {
		this.myCoordinate = coordinate;
	}

	@Override
	public Map<String, String> getAttributeStrings() {
		Map<String, String> myAttrMap = new TreeMap<String, String>();
		for (String s : myCharacteristics.keySet()) {
			myAttrMap.put(s, myCharacteristics.get(s).toString());
		}
		return myAttrMap;
	}

	@Override
	public String getImageURI() {
		return getImagePath();
	}

	@Override
	public List<String> getActionNames() {
		// TODO Auto-generated method stub
		return getEventStrings();
	}

	@Override
	public int getPlayerID() {
		// TODO Auto-generated method stub
		return myOwnerID;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return myLayer;
	}

	@Override
	public ChangedParameters performAction(String action, ATargetCoordinate coor) {
		// TODO Auto-generated method stub
		return this.myEventHandler.executeAction(this.myEvents.get(action), this, (TargetCoordinateSingle) coor);
	}

	@Override
	public GridCoordinateParameters performRequest(String action) {
		// TODO Auto-generated method stub
		return myEventHandler.executeRequest(this.myEvents.get(action), this);
	}

	public void setOwnerID(int playerID) {
		myOwnerID = playerID;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface#isDead()
	 *
	 * If this MapObject has HealthCharacteristic, then this method returns
	 * whether the health is 0. Otherwise, the MapObject is considered alive so
	 * the method returns false.
	 */
	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return this.hasCharacteristic("HealthCharacteristic")
				&& (((HealthCharacteristic) getCharacteristic("HealthCharacteristic")).getCurrentHealth() <= 0);
	}

	@Override
	public List<ATargetCoordinate> getActionTargets(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRemoved() {
		// TODO Auto-generated method stub
		return isDead();
	}

	@Override
	public String getName() {
		return myName;
	}

	public String getImagePath() {
		return myImagePath;
	}

	@Override
	public String toString() {
		return myName;
	}

	public void addEvent(String name, MapObjectEvent action) {
		this.myEvents.put(name, action);
	}

	public List<String> getEventStrings() {
		return generateSortedListFromStringSet(this.myEvents.keySet());
	}

	public boolean hasEvent(String name) {
		return this.myEvents.containsKey(name);
	}

public void setMapObjectEventHandler(MapObjectEventHandler handler){
	this.myEventHandler = handler;
	}

	@Override
	public IGamePlayerMapObjectAction getAction(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOwnerID() {
		// TODO Auto-generated method stub
		return this.myOwnerID;
	}

	@Override
	public List<String> getAllCharacteristicNames() {
		// TODO Auto-generated method stub
		return generateSortedListFromStringSet(this.myCharacteristics.keySet());
	}

	private List<String> generateSortedListFromStringSet(Set<String> set) {
		List<String> list = new ArrayList<>(set);
		Collections.sort(list);
		return list;
	}
	
	public MapObject clone(){
		MapObject result = new MapObject(this.myCoordinate.clone(),this.myOwnerID,this.myLayer,this.myName,this.myImagePath);
		result.myCharacteristics = new HashMap<>();
		for(String key: this.myCharacteristics.keySet()){
			result.myCharacteristics.put(key, this.myCharacteristics.get(key).clone());
		}
		result.myEventHandler = this.myEventHandler.clone(this);
		result.myEvents = new HashMap<>(this.myEvents);
		return result;
	}

	@Override
	public Boolean containsCharacteristic(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	public static void main(String[] args){
		MapObject mo = new MapObject(new TargetCoordinateSingle(50,50),0,0,"test","test");
		mo.addCharacteristic("HealthCharacteristic", new HealthCharacteristic());
		HealthCharacteristic hc = (HealthCharacteristic) mo.getCharacteristic("HealthCharacteristic");
		System.out.println(hc.getCurrentHealth());
		MapObject mo1 = mo.clone();
		HealthCharacteristic hc1 = (HealthCharacteristic) mo1.getCharacteristic("HealthCharacteristic");
		System.out.println(hc1.getCurrentHealth());
		hc1.changeHealth(50);
		System.out.println(hc1.getCurrentHealth());
		System.out.println(hc.getCurrentHealth());
	}
	*/
}
