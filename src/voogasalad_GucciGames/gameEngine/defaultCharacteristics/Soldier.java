
package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

/**
 *
 * @author Sally Al
 *
 */
public class Soldier extends AMapObjectCharacteristic {
	private Boolean bol = false;

	public Soldier(Boolean soldier) {
		setBol(soldier);
	}

	public Boolean getBol() {
		return bol;
	}

	public void setBol(Boolean bol) {
		this.bol = bol;
	}

}
