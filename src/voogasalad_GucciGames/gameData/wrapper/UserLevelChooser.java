package voogasalad_GucciGames.gameData.wrapper;
//this is part of my masterpiece
public class UserLevelChooser implements ILevelChooser{
	//this is just another example algorithm that could be used
	
	private GameStats myGameStats;
	private String currentChoice;
	
	public UserLevelChooser(){
		myGameStats = new GameStats();
		currentChoice = myGameStats.getMyCurrentLevel();
	}
	
	public void setUserInput(String userChoice){
		//gameplayer shows splash screen of chooseable levels?
		//and the level they choose is sent here
		currentChoice = userChoice;
	}
	
	@Override
	public String nextLevel() {
		return currentChoice;
	}
	
}
