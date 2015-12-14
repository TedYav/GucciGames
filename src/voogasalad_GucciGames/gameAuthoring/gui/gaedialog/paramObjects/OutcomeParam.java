package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.ArrayList;
import java.util.List;

public class OutcomeParam {

	private List<ObjectParam> conditions;
	private ObjectParam objParam;

	private String name;

	public OutcomeParam(String name, int ownerId, ObjectParam objParam) {
		this.name = name;
		this.objParam = objParam;
		this.conditions = new ArrayList<ObjectParam>();
	}

	public void setConditions(List<ObjectParam> items) {
		this.conditions.addAll(items);
	}

}
