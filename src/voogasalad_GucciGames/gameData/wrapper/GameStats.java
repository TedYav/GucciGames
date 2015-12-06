package voogasalad_GucciGames.gameData.wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;

//why can we just have this class be where all initialized variables are stored
//so everything after that gets it's variables from here
//and i don't have to think about how to pass things in.

public class GameStats {
	private Map<Integer, Map<String,APlayerChars>> transferables;
	
	public GameStats(){
		transferables = new HashMap<>();
	}
	
	public void clear(){
		this.transferables.clear();
	}
	
	public void addTransferableCharacteristic(Integer id, APlayerChars transfer, String name){
		if (transfer != null){	
			if(transferables.containsKey(id)){
				transferables.get(id).put(name,transfer);
			}
			else{
				Map<String,APlayerChars> temp = new HashMap<>();
				temp.put(name,transfer);
				transferables.put(id, temp);
			}
		}
	}
	
	
	public boolean contains(int id){
		return this.transferables.containsKey(id);
	}
	
	public Map<String,APlayerChars> getCharacteristics(int id){
		return this.transferables.get(id);
	}


	
}
