package voogasalad_GucciGames.gameplayer.controller;

import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;

public interface GameEngineToGamePlayerInterface {

	public String getGameName();
	
	public List<PlayerMapObjectInterface> getInitialState();
	
	public void endTurn();
	
	public int getTurnPlayerID();
	
	public GridCoordinateParameters getPossibleCoordinates(String action, PlayerMapObjectInterface mapObject);
}
