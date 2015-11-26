package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.CommunicationParams.ActionToGamePlayerParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.objectActions.IGamePlayerMapObjectAction;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEventHandler;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class MapObject implements PlayerMapObjectInterface{
	private ATargetCoordinate myCoordinate;
	private int myOwnerID;
	private int myLayer;
	private String myName;
	private String myImagePath;
	private  Map<String, AMapObjectCharacteristic> myCharacteristics;
	private MapObjectEventHandler myEventHandler;
	private double myWidth, myHeight, myX, myY;

	public MapObject(ATargetCoordinate coor, int id, int layer, 
			String name, String imagePath, double x, double y, double width, double height){
		this.myCoordinate = coor;
		this.myOwnerID = id;
		this.myLayer = layer;
		this.myName = name;
		this.myImagePath = imagePath;
		this.myHeight = height;
		this.myWidth = width;
		this.myX = x;
		this.myY = y;
		this.myCharacteristics = new TreeMap<>();
		this.myEventHandler = new MapObjectEventHandler();
	}
	
	public MapObject(String name, String imagePath){
		this(null,-1,0,name,imagePath,0,0,0,0);
	}
	
	public MapObject(MapObject obj, ATargetCoordinate coor, int id){
		this(obj,coor,id,0);
	}
	
	public MapObject(MapObject obj, ATargetCoordinate coor, int id, int layer){
		this(coor,id,layer,obj.getName(),obj.getImagePath(),obj.getX(),obj.getY(),obj.getWidth(),obj.getHeight());
	}
	
	public MapObject(String name, String imagePath, double x, double y, double width, double height){
		this(null,-1,0,name,imagePath,x,y,width,height);
	}
	
	public MapObject(ATargetCoordinate coor, int id, int layer, 
			String name, String imagePath){
		this(coor,id,layer,name,imagePath,0,0,0,0);
	}
	
	public MapObject(ATargetCoordinate coor, int ownerID){
		this(coor,ownerID,0,"","");
	}

	public MapObject(ATargetCoordinate coor, int ownerID, int layer){
		this(coor,ownerID,layer,"","");
	}

	public AMapObjectCharacteristic getCharacteristic(String name){
		return this.myCharacteristics.get(name);
	}

	public void addCharacteristic(String name, AMapObjectCharacteristic characteristic){
		this.myCharacteristics.put(name, characteristic);
	}

	public boolean hasCharacteristic(String name){
		return this.myCharacteristics.containsKey(name);
	}

	public List<String> getCharacteristicStrings(){
		return new ArrayList<String>(this.myCharacteristics.keySet());
	}

	public boolean isTile(){
		return myCharacteristics.containsKey("TileCharacteristic") || this.getName().equals("TileCharacteristic");
	}

	@Override
	public ATargetCoordinate getCoordinate(){
		return myCoordinate;
	}

	public void setCoordinate(ATargetCoordinate coordinate){
		this.myCoordinate = coordinate;
	}

	public boolean isUnit() {
		return this.hasCharacteristic("unit");
	}

	@Override
	public Map<String, String> getAttributeStrings() {
		//return myObjectType.getCharacteristic();
		Map<String, String> myAttrMap = new TreeMap<String, String>();
		for(String s : myCharacteristics.keySet()){
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

	public ActionToGamePlayerParameters performAction(String action, ATargetCoordinate coor) {
		// TODO Auto-generated method stub

		//System.out.println(myObjectType);
		//System.out.println("action"+myObjectType.getAction(action));
		//return (ActionToGamePlayerParameters) getAction(action).executeAction(basicParameters, myOwnerID);
		return (ActionToGamePlayerParameters) myEventHandler.executeAction(action, this, (TargetCoordinateSingle) coor);
	}

	public GridCoordinateParameters performRequest(String action) {
		// TODO Auto-generated method stub
		return (GridCoordinateParameters) myEventHandler.executeRequest(action, this);
	}
	public void setOwnerID(int playerID) {
		myOwnerID = playerID;
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return ((HealthCharacteristic) getCharacteristic("HealthCharacteristic")).getCurrentHealth() < 0;
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

	public String getName() {
		return myName;
	}

	public String getImagePath() {
		return myImagePath;
	}

	@Override
	public String toString(){
		return myName;
	}


	public void addEvent(String name, MapObjectEvent action){
		this.myEventHandler.addEvent(name, action);
	}

	public List<String> getEventStrings(){
		return this.myEventHandler.getEventNames();
	}

	public boolean hasEvent(String name){
		return this.myEventHandler.hasEvent(name);
	}

	public double getWidth(){
		return this.myWidth;
	}
	
	public double getHeight(){
		return this.myHeight;
	}
	
	public double getX(){
		return this.myX;
	}
	
	public double getY(){
		return this.myY;
	}
	
	public void setXY(double x, double y){
		this.myX = x;
		this.myY = y;
	}
	
	public void setWidthHeight(double width, double height){
		this.myWidth = width;
		this.myHeight = height;
	}
	
	public void setBasicParameters(BasicParameters basic){
		this.myEventHandler.setBasicParameters(basic);
	}

	@Override
	public IGamePlayerMapObjectAction getAction(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOwnerID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getAllCharacteristicNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	public Map<String, String> getAttributes() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hello", "hi"); //change this later
		return map;
	}

	public boolean hasDefaultCharacteristic(String name){
		return myDefaultCharacteristics.containsKey(name);

	}

	public boolean isTile() {
		return myDefaultCharacteristics.containsKey("TileCharacteristic") || myName.equals("TileCharacteristics");
	}

	public void addDefaultCharacteristic(String name, AMapObjectCharacteristic func){
		this.myDefaultCharacteristics.put(name, func);
	}
	 */
}
