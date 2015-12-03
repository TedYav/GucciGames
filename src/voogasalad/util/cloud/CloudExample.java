package voogasalad.util.cloud;

public class CloudExample {

	// this is an example of how to add a score
	public static void main(String[] args){
		
		Cloud c = new Cloud();
		String gameName = "Duvall Tag";
		String playerName = "Superman";
		double score = 99999999;
		
		c.addHighScore(gameName, playerName, score);
		System.out.println(c.retrieveHighScores(gameName));
		
	}
	
}
