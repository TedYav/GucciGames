package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

public class GameSettingParams extends AParamsObject{
	
	private String name="";
	private String mapSize="";
	private boolean fogOfWar=false;
	private double numberOfPlayers=0;
	
	public GameSettingParams(){
		
	}
	
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

	public double getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(double d) {
		this.numberOfPlayers = d;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	


}
