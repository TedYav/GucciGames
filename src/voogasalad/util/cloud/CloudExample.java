package voogasalad.util.cloud;

public class CloudExample {

	// this is an example of how to add a score
	public static void main(String[] args) {

		Cloud c = new Cloud();
		String gameName = "Enter a game name here to test";
		String playerName = "Enter a player name here to test";
		double score = 999999;

		c.addHighScore(gameName, playerName, score);
		System.out.println(c.retrieveHighScores(gameName));

	}

}
