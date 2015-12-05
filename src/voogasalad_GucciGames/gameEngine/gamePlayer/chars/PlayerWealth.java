/**
 *
 */
package voogasalad_GucciGames.gameEngine.gamePlayer.chars;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerWealth extends APlayerChars {
	private int myWealth = 100;

	public PlayerWealth(){}
	public PlayerWealth(int wealth){
		myWealth = wealth;
	}
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
