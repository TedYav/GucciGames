package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.CharacteristicParams;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionParams;

public abstract class AMapObjectCharacteristic {
	
	protected CharacteristicParams myCharParams;
	
	//this has to disappear, I have no idea why this is here
	public AMapObjectCharacteristic(CharacteristicParams charParams){
		myCharParams = charParams;
	}

	public void set(List<Integer> values) {
		// TODO Auto-generated method stub
		
	};
	
	public abstract String toString();
}
