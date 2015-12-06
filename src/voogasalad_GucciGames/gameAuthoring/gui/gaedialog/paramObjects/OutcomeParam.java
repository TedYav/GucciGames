package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.ArrayList;
import java.util.List;

public class OutcomeParam {
	
	private List<String> conditions;
	private ObjParam objParam;
	
	private String name;
	
	public OutcomeParam(String name, int ownerId, ObjParam objParam){
		this.name = name;
		this.objParam = objParam;
		this.conditions = new ArrayList<String>();		
	}
	
	public void setConditions(List<String> items){
		this.conditions.addAll(items);
	}

}
