package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

public class GameSettingParams {
	
	private String name;
	private String mapSize;
	private boolean fogOfWar;
	private int numberOfPlayers;
	
	public GameSettingParams(String name, String mapSize, boolean fogOfWar, int numberOfPlayers){
		this.setName(name);
		this.setMapSize(mapSize);
		this.setFogOfWar(fogOfWar);
		this.setNumberOfPlayers(numberOfPlayers);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMapSize() {
		return mapSize;
	}

	public void setMapSize(String mapSize) {
		this.mapSize = mapSize;
	}

	public boolean isFogOfWar() {
		return fogOfWar;
	}

	public void setFogOfWar(boolean fogOfWar) {
		this.fogOfWar = fogOfWar;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	


}
