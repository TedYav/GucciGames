package voogasalad_GucciGames.gameEngine.gameUnit;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.mapObject.MapObjectAction;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;


public class GameUnitType extends MapObjectType{

	public GameUnitType(String name, String imagePath) {
		super(name, imagePath);
		// TODO Auto-generated constructor stub
	}
	
	public GameUnitType(String name, String imagePath, 
			Map<String, MapObjectAction> abilities) {
		super(name, imagePath);
		this.myAbilities = abilities;
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}