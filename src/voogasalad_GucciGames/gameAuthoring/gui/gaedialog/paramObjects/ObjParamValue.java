package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.HashMap;
import java.util.Map;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;


public class ObjParamValue {

    private String objName;
    private Map<String, String> paramValues = new HashMap<String, String>();
    private MapObjectType mapObjectType;

    public ObjParamValue (String objName, ObjType type, MapObjectType mapObjectType) {
        this.objName = objName;
        this.mapObjectType = mapObjectType;
    }

    public ObjParamValue (MapObjectType mapObjectType) {
        this.mapObjectType = mapObjectType;
    }

    public void setObjName (String name) {
        this.objName = name;
    }

    public void setObjType (ObjType type) {
    }

    public String getName () {
        return objName;
    }

    public Map<String, String> getParamValues () {
        return this.paramValues;
    }

    public void setParamValues (Map<String, String> map) {
        map.forEach( (k, v) -> {
            paramValues.put(k, v);
        });
    }

    public int numParams () {
        return paramValues.size();
    }

    public Map<String, String> getMap () {
        return paramValues;
    }

    public MapObjectType getMapObjectType () {
        return mapObjectType;
    }

}