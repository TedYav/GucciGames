package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;
import java.util.HashMap;
import java.util.Map;


public class ObjParam{
	
	private String name;
	private Map<String, String> params = new HashMap<String, String>();
	private ObjType type;
	private int ownerId;
	
	public ObjParam(String name, ObjType type, int ownerId){
		this.name = name;
		this.type = type;
		this.ownerId = ownerId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;		
	}
	
	public void addParam(String type, String name){
		params.put(name, type);
	}
	
	public void removeParam(String name){
		params.remove(name);
	}

	public Map<String, String> getAllParams() {
		return params;
	}
	
	public ObjType getObjType(){
		return type;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}


}
