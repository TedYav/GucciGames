package voogasalad_GucciGames.gameEngine.gamePlayer.chars;

public class PlayerScore extends APlayerChars{

	private double myScore;
	
	public PlayerScore(){
		this.myScore = 0;
	}
	
	public PlayerScore(double score){
		this.myScore = score;
	}
	
	public void setScore(double score){
		this.myScore = score;
	}
	
	public double getScore(){
		return this.myScore;
	}
	
}
