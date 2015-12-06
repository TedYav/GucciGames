package voogasalad_GucciGames.gameEngine;

import com.thoughtworks.xstream.XStream;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

public abstract class GameEnginePlayer {

    private GameEngine mySuperEngine;
	private GameLevelEngine myEngine;

	public void setLevelEngine(GameLevelEngine currentLevel) {
		setMyEngine(currentLevel);
	}


    public GameEnginePlayer(GameEngine engine){
    	mySuperEngine = engine;
    }
    

	public void updateGameEngine(String engineXML) {
		XStream xstream = new XStream();
		setMyEngine((GameLevelEngine) xstream.fromXML(engineXML));
		this.getMySuperEngine().addLevel(getMyEngine().getLevelName(), getMyEngine());

	}

    
	protected GameEngine getMySuperEngine() {
		return mySuperEngine;
	}

	protected void setMySuperEngine(GameEngine mySuperEngine) {
		this.mySuperEngine = mySuperEngine;
	}


	protected GameLevelEngine getMyEngine() {
		return myEngine;
	}


	protected void setMyEngine(GameLevelEngine myEngine) {
		this.myEngine = myEngine;
	}


	public abstract void endTurn();

	
}
