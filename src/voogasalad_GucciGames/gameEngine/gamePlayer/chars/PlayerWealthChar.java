/**
 *
 */
package voogasalad_GucciGames.gameEngine.gamePlayer.chars;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerWealthChar extends APlayerChars {
	private int myWealth = 100;

	public PlayerWealthChar(){}
	public PlayerWealthChar(int wealth){
		myWealth = wealth;
	}
	public int getWealth() {
		return myWealth;
	}

	public void setWealth(int wealth) {
		myWealth = wealth;
	}

	public void modifyWealth(int delta) {
		myWealth = myWealth + delta;
	}

}
