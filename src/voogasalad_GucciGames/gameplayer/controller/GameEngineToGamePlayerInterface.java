package voogasalad_GucciGames.gameplayer.controller;

import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;

public interface GameEngineToGamePlayerInterface {

	public String getGameName();
	
	public List<PlayerMapObjectInterface> getInitialState();
	
	public void endTurn();
	
	public int getTurnPlayerID();
}
