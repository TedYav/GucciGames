package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

public class CharacteristicParams {
	
	private String myName;
	
	public CharacteristicParams (String characteristicName){
		this.myName = characteristicName;
	}
	
	public String getName(){
		return this.myName;
	}


}
