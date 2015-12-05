/**
 *
 */
package voogasalad_GucciGames.gameEngine.gamePlayer;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerWealth extends AGamePlayerPersonCharacteristic {
	private int myWealth = 0;


	public int status() {
		return myWealth;
	}

	public void setWealth(int wealth) {
		myWealth = wealth;
	}

	public void modifyWealth(int delta) {
		myWealth = myWealth + delta;
	}

}
