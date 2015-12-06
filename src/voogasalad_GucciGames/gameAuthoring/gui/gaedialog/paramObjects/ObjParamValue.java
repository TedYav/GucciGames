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
	
	public ObjParamValue(){
		
	}
	
	public void setObjName(String name){
		this.objName = name;
	}
	
	public void setObjType(ObjType type){
		this.type = type;
	}
	
	public String getName(){
		return objName;
	}
	
	public Map<String, String> getParamValues(){
		return this.paramValues;
	}
	
		
	public void setParamValues(Map<String, String> map){
		System.out.println("setting chars");
		map.forEach((k,v) -> {
			System.out.println(k);
			System.out.println(v);
			paramValues.put(k, v);
		});
	}
	
	

}
