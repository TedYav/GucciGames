package voogasalad_GucciGames.gameEngine;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

public abstract class GameEnginePlayer implements Runnable {

    private GameEngine mySuperEngine;
	private GameLevelEngine myEngine;

	public void setLevelEngine(GameLevelEngine currentLevel) {
		setMyEngine(currentLevel);
	}


    public GameEnginePlayer(GameEngine engine){
    	mySuperEngine = engine;
    }
    

	public void updateGameEngine(String engineXML) {
		XStream xstream = new XStream(new DomDriver());
		System.out.println(engineXML.length());
		setMyEngine((GameLevelEngine) xstream.fromXML(engineXML));
		System.out.println(getMyEngine().getLevelName());
		System.out.println("level stuff");
		this.getMySuperEngine().setLevel(getMyEngine().getLevelName(), getMyEngine());

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
