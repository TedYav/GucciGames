package voogasalad_GucciGames.gameEngine.CommunicationParameters;

import java.util.List;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;
import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class BasicParameters extends CommunicationParameters implements Cloneable{

	// Classes which extend this will be used to share information between the
	// front and back end
	private MapObject myCalledMe;
	private GameEngine myEngine;
	private List<APlayerChars> transfers;

	public BasicParameters(MapObject calledMe, GameEngine engine) {
		this.myCalledMe = calledMe;
		this.myEngine = engine;
	}

	public BasicParameters(GameEngine engine) {
		this.myCalledMe = null;
		this.myEngine = engine;
	}

	public BasicParameters(BasicParameters params, MapObject calledMe) {
		this(calledMe, params.getEngine());
	}

	public BasicParameters(BasicParameters params) {
		this(params, params.getCalledMe());
	}

	public GameLevelEngine getLevelEngine() {
		return this.myEngine.getCurrentLevel();
	}

	public GameEngine getEngine(){
		return this.myEngine;
	}
	
	public int getTurn() {
		return this.myEngine.getCurrentLevel().getTurn();
	}

	public MapObject getCalledMe() {
		return myCalledMe;
	}

	public List<APlayerChars> getTransfers(){
		return this.transfers;
	}
	
	public BasicParameters clone(MapObject mo) {
        try {
        	BasicParameters basic = (BasicParameters) super.clone();
        	basic.myCalledMe = mo;
        	return basic;
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
}
