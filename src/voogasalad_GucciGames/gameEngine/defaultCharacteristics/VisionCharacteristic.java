package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class VisionCharacteristic extends AMapObjectCharacteristic{

	private int myVision;
	
	public VisionCharacteristic(int vision){
		this.myVision = vision;
	}
	
	
		public int getVision(){
			return myVision;
		}
	
		public void setVision(int vis){
			myVision = vis;
		}
	
}
