package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

public class StructureParams extends AParamsObject {
	
	private String structureName;
	
	public StructureParams(String name){
		this.setName(name);
	}

	public String getName() {
		return structureName;
	}

	public void setName(String structureName) {
		this.structureName = structureName;
	}
	
	

}
