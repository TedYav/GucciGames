package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.ArrayList;
import java.util.List;

public class OutcomeParam {
	
	private List<ObjParam> conditions;
	private ObjParam objParam;
	
	private String name;
	
	public OutcomeParam(String name, int ownerId, ObjParam objParam){
		this.name = name;
		this.objParam = objParam;
		this.conditions = new ArrayList<ObjParam>();		
	}
	
	public void setConditions(List<ObjParam> items){
		this.conditions.addAll(items);
	}

}
