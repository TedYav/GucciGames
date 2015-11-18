package voogasalad_GucciGames.gameAuthoring;

import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;

public interface IDialogGaeController {
	public void createCustomMapObject(ObjectProperty p);
	
	public void setNumberOfPlayers(int n);
	
	public int getNumberOfPlayers();

}
