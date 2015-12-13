package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class VisionCharacteristic extends AMapObjectCharacteristic {

	private int Vision;

	public VisionCharacteristic(int vision) {
		this.Vision = vision;
	}

	public int getVision() {
		return Vision;
	}

	public void setVision(int vis) {
		Vision = vis;
	}

}
