
package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

/**
 *
 * @author Sally Al
 *
 */
public class GroundUnit extends AMapObjectCharacteristic {
	private Boolean bol = false;

	public GroundUnit(Boolean groundUnit) {
		setBol(groundUnit);

	}

	public Boolean getBol() {
		return bol;
	}

	public void setBol(Boolean bol) {
		this.bol = bol;
	}
}
