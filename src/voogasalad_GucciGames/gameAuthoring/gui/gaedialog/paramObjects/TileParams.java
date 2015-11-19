package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

public class TileParams extends AParamsObject{
	private String tileName;
	
	public TileParams(String tileName){
		this.setName(tileName);
	}

	public String getName() {
		return tileName;
	}

	public void setName(String tileName) {
		this.tileName = tileName;
	}

}
