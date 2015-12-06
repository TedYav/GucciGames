package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.HashMap;
import java.util.Map;

public class ObjParamValue {
	
	private ObjType type;
	private String objName;
	private Map<String, String> paramValues = new HashMap<String, String>();
	
	public ObjParamValue(String objName, ObjType type){
		this.objName = objName;

		this.type = type;		
	}
	
		
	public void setParamValues(Map<String, String> map){
		this.paramValues = map;
	}
	
	

}
