package voogasalad_GucciGames.gameData.wrapper;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import voogasalad_GucciGames.gameEngine.MainGameEngine;

public class GameLevel implements IGameLevelToGamePlayer, IGameLevelToGameData{
	//@XStreamOmitField
	private MainGameEngine myEngine;
	
	private int myID;
	private int myNextLevelID;
	private boolean myChoosability;
	private String myGameLevelName;
	
	public GameLevel(int id, int nextID, String name, boolean chooseable){
		this(id, nextID, name, chooseable, null);
	}
	
	public GameLevel(int id, int nextID, String name, boolean choosable, MainGameEngine engine){
		this.myID = id;
		this.myNextLevelID = nextID;
		this.myGameLevelName = name;
		this.myChoosability = choosable;
		this.myEngine = engine;
	}
	
	protected void changeID(int newid){
		this.myID = newid;
	}
	
	@Override
	public int getID() {
		return this.myID;
	}
	
	@Override
	public int getNextLevel() {
		return this.myNextLevelID;
	}
	
	@Override
	public boolean isChoosable() {
		// TODO Auto-generated method stub
		return this.myChoosability;
	}
	
	public void setChoosable(boolean chooseable){
		myChoosability = chooseable;
	}
	
	public void setNextLevel(int nextLevelID){
		myNextLevelID = nextLevelID;
	}
	
	public void setName(String name){
		myGameLevelName = name;
	}
	
	/**
	 * Assigns an engine to the level.
	 * Called when saving the game.
	 * @param engine
	 */
	public void assignEngine(MainGameEngine engine){
		myEngine = engine;
	}
	
	@Override
	public String getLevelName() {
		// TODO Auto-generated method stub
		return this.myGameLevelName;
	}
	
	@Override
	public MainGameEngine getGameEngine() {
		// TODO Auto-generated method stub
		return this.myEngine;
	}

}
