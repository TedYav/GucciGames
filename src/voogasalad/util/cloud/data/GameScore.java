// This entire file is part of my masterpiece.
// Ted Yavuzkurt
package voogasalad.util.cloud.data;

public interface GameScore {

	/**
	 * Returns the Player Name associated with the score, if one was set.
	 * 
	 * @return
	 */
	public String getPlayerName();

	/**
	 * Returns the value of the score.
	 * 
	 * @return
	 */
	public Double getScore();

	/**
	 * Returns the title of the score. If none is set, returns the time the
	 * score was reached.
	 * 
	 * @return
	 */
	public String getTitle();

	/**
	 * Returns a String representation of the form PlayerName -- Score
	 * 
	 * @return
	 */
	public String toString();

}
