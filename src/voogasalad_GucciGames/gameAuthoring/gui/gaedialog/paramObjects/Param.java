package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

public class Param {
	private String type;
	private String name;
	
	public Param(String type, String name){
		this.setType(type);
		this.setName(name);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
