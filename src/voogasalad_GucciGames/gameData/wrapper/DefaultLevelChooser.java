package voogasalad_GucciGames.gameData.wrapper;
//this is part of my masterpiece

public class DefaultLevelChooser implements ILevelChooser{
	//incremental levels, based on order in levelsMap
	
	private GameStats myGameStats;
	
	public DefaultLevelChooser(){
		myGameStats = new GameStats();
	}

	@Override
	public String nextLevel() {
		boolean found = false;
		for(String key: myGameStats.getLevelsMap().keySet()){
			if (found){
				myGameStats.setMyCurrentLevel(key);
				return key;
			}
			if (key == myGameStats.getMyCurrentLevel()){
				found = true;
			}
		}
		return ""; //means either key not found which is an error
					//or no more levels left and you need to return to main menu
	}

}
