package voogasalad_GucciGames.helpers;

import java.util.ResourceBundle;

import voogasalad.util.fxsprite.Sprite;

public class SpriteDatabase implements ResourceDatabase<Sprite>{

	private ResourceManager myManager;
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.helpers.config.SpriteDatabase");
	
	public SpriteDatabase(ResourceManager manager){
		myManager = manager;
	}
	
	@Override
	public Sprite request(String URI) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
