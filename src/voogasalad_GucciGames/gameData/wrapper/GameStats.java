package voogasalad_GucciGames.gameData.wrapper;
//this is part of my masterpiece
import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;

//why can we just have this class be where all initialized variables are stored
//so everything after that gets it's variables from here
//and i don't have to think about how to pass things in.

public class GameStats {
	private Map<Integer, Map<String,APlayerChars>> transferables;
	private ILevelChooser levelChooser;
	private Map<String, GameLevelEngine> myLevelsMap;
	private String myCurrentLevel;
	
	public GameStats(){
		myLevelsMap = new HashMap<String, GameLevelEngine>();
		transferables = new HashMap<>();
	}
	
	public String nextLevel(){
		return levelChooser.nextLevel();
	}
	
	public Map<String, GameLevelEngine> getLevelsMap() {
		return myLevelsMap;
	}

	public String getMyCurrentLevel() {
		return myCurrentLevel;
	}

	public void setMyCurrentLevel(String myCurrentLevel) {
		this.myCurrentLevel = myCurrentLevel;
	}

	public void setLevelsMap(Map<String, GameLevelEngine> myLevelsMap) {
		this.myLevelsMap = myLevelsMap;
	}



	public void setLevelChooser(ILevelChooser choose){
		levelChooser = choose;
	}
	
	public ILevelChooser getLevelChooser(){
		return this.levelChooser;
	}
	
	public void clear(){
		this.transferables.clear();
	}

	public void addTransferableCharacteristic(Integer id, APlayerChars transfer, String name) {
		if (transfer != null) {
			if (transferables.containsKey(id)) {
				transferables.get(id).put(name, transfer);
			} else {
				Map<String, APlayerChars> temp = new HashMap<>();
				temp.put(name, transfer);
				transferables.put(id, temp);
			}
		}
	}

	public boolean contains(int id) {
		return this.transferables.containsKey(id);
	}

	public Map<String, APlayerChars> getCharacteristics(int id) {
		return this.transferables.get(id);
	}

}
