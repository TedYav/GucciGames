package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

public interface IMapObjectTypes {
	
	public void changeName(String id, String name);
	
	public void addRule(String id, String action);
	
	public void removeRule(String id, String action);
	
	public void removeCharacteristic(String id, String characteristicName);
	
	public void addCharacteristic(String id, String characteristicName, double param );
	
	public void changeCharacteristicParam(String id, Double param);
	
	public void addAction(String id, String actionName, List<String> rules);
	
	public void removeAction(String id, String actionName);
	
	public void changeImage(String id, String path);
	
	public void addGroovy(String groovy);

}
