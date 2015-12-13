package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class ObjParamValue {

	private ObjType type;
	private String objName;
	private Map<String, String> paramValues = new HashMap<String, String>();
	private MapObjectType mapObjectType;

	public ObjParamValue(String objName, ObjType type, MapObjectType mapObjectType) {
		this.objName = objName;
		this.mapObjectType = mapObjectType;
		this.type = type;
	}

	public ObjParamValue(MapObjectType mapObjectType) {
		this.mapObjectType = mapObjectType;
	}

	public void setObjName(String name) {
		this.objName = name;
	}

	public void setObjType(ObjType type) {
		this.type = type;
	}

	public String getName() {
		return objName;
	}

	public Map<String, String> getParamValues() {
		return this.paramValues;
	}

	public void setParamValues(Map<String, String> map) {
		System.out.println("setting chars");
		map.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(v);
			paramValues.put(k, v);
		});
	}

	public int numParams() {
		return paramValues.size();
	}

	public Map<String, String> getMap() {
		return paramValues;
	}

	public MapObjectType getMapObjectType() {
		return mapObjectType;
	}

}
