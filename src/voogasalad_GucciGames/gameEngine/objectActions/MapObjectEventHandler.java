package voogasalad_GucciGames.gameEngine.objectActions;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class MapObjectEventHandler implements Cloneable{
	
	private BasicParameters myBasic;
	
	public MapObjectEventHandler(BasicParameters basic){
		this.myBasic = basic;
	}
	
	public ChangedParameters executeAction(MapObjectEvent event, MapObject obj, 
			TargetCoordinateSingle coor){
		return event.executeAction(new LocationParameters(myBasic,obj,coor),obj.getPlayerID());
	}
	
	public GridCoordinateParameters executeRequest(MapObjectEvent event, MapObject obj){
		return event.executeRequest(new BasicParameters(myBasic,obj),obj.getPlayerID());
	}
	
	public MapObjectEventHandler clone(MapObject mo) {
        try {
        	MapObjectEventHandler handler = (MapObjectEventHandler) super.clone();
        	handler.myBasic = handler.myBasic.clone(mo);
        	return handler;
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }

}
