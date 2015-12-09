package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

/**
 *
 * @author Sally Al
 *
 */
public class Tower extends AMapObjectCharacteristic{
	private Boolean bol = false;
	public Tower (Boolean tower){
		setBol(tower);

	}
	public Boolean getBol() {
		return bol;
	}
	public void setBol(Boolean bol) {
		this.bol = bol;
	}

}
