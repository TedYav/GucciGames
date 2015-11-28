package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.LocationParameters;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class MapObjectEventHandler {
	
	private BasicParameters myBasic;
	private Map<String, MapObjectEvent> myEvents; //test
	
	public MapObjectEventHandler(BasicParameters basic, Map<String, MapObjectEvent> events){
		this.myBasic = basic;
		this.myEvents = events;
	}
	
	public MapObjectEventHandler(BasicParameters basic){
		this(basic, new TreeMap<>());
	}
	
	public MapObjectEventHandler(){
		this(null,new TreeMap<>());
	}
	
	public CommunicationParameters executeAction(String name, MapObject obj, 
			TargetCoordinateSingle coor){
		return this.myEvents.get(name).executeAction(new LocationParameters(myBasic,obj,coor),obj.getPlayerID());
	}
	
	public CommunicationParameters executeRequest(String name, MapObject obj){
		return this.myEvents.get(name).executeRequest(new BasicParameters(myBasic,obj),obj.getPlayerID());
	}
	
	public void setBasicParameters(BasicParameters basic){
		this.myBasic = basic;
	}
	
	public boolean hasEvent(String eventName){
		return this.myEvents.containsKey(eventName);
	}
	
	public void addEvent(String eventName, MapObjectEvent event){
		this.myEvents.put(eventName, event);
	}
	
	public List<String> getEventNames(){
		return new ArrayList<String>(this.myEvents.keySet());
	}

}
