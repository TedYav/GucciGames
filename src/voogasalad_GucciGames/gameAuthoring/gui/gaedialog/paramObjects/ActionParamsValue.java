package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

import java.util.HashSet;
import java.util.Set;

import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class ActionParamsValue extends AParams{
	
	private Set<String> myRules = new HashSet<String>();
	private Set<ObjParamValue> myCharacteristics = new HashSet<ObjParamValue>();
	private Set<OutcomeParamValue> myOutcomes = new HashSet<OutcomeParamValue>();
	
	private String name;
	private MapObjectType mapObjectType;
	
	public ActionParamsValue(MapObjectType mapObjectType){
		this.mapObjectType = mapObjectType;
	}
	
	public ActionParamsValue(String name){
		this.name = name;
	}
	

	public void addRule(String ruleName){
		myRules.add(ruleName);
	}
	public void addCharacteristics(ObjParamValue characteristics){
		myCharacteristics.add(characteristics);
	}
	
	public void addOutcome(OutcomeParamValue outcomeName){
		myOutcomes.add(outcomeName);
	}
	
	public Set<OutcomeParamValue> getAllOutcomes(){
		return myOutcomes;
	}
	public Set<String> getAllRules(){
		return myRules;
	}
	
	public Set<ObjParamValue> getAllCharacteristics(){
		return myCharacteristics;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;		
	}


	@Override
	public void print() {
	
		System.out.println("name: " + this.name);
		System.out.println("Characteristics: ");
		myCharacteristics.forEach(e -> {
			System.out.println(e.getName());
		});
		
		System.out.println("Rules: ");
		myRules.forEach(e -> {
			System.out.println(e);
		});
		
		System.out.println("Outcomes: ");
		myOutcomes.forEach(e -> {
			System.out.println(e.getName());
		});
		
		System.out.println("Condition: ");
		this.myOutcomes.forEach(outcome -> {
			outcome.getConditions().forEach(con -> {
				System.out.println("condition name: " + con.getName());
				con.getParamValues().forEach((k,v) -> {
					System.out.println("condition k: " + "condition v" + v);
				});
			});
			
			
		});
		
		
	}
	
	public MapObjectType getMapObjectType() {
		return mapObjectType;
	}
	
	public Set<OutcomeParamValue> getOutcomes() {
		return myOutcomes;
	}
	
	public Set<String> getRules() {
		return myRules;
	}
	

}
