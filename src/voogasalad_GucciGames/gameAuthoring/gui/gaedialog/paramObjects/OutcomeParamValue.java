package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class OutcomeParamValue {

	private List<ObjParamValue> conditions;
	private ObjParamValue objParam;
	
	private String name;
	
	public OutcomeParamValue (String name, MapObjectType type, ObjParamValue objParam){
		this.name = name;
		this.objParam = objParam;
		this.conditions = new ArrayList<ObjParamValue>();		
	}
	
	public void setConditions(List<ObjParamValue> items){
		this.conditions.addAll(items);
	}

}