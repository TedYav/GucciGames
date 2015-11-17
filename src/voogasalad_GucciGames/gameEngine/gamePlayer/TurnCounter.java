package voogasalad_GucciGames.gameEngine.gamePlayer;

public class TurnCounter {
	
	int currentTurn;
	
	public TurnCounter(int currTurn){
		currentTurn = currTurn;
	}
	
	public TurnCounter(){
		this(0);
	}
	
	public void update(){
		currentTurn++;
	}
	
	public void setCurrentTurn(int currTurn){
		currentTurn = currTurn;
	}
	
	public int getCurrentTurn(){
		return currentTurn;
	}

}
