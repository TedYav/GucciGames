package voogasalad_GucciGames.gameEngine.CommunicationParameters;

import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class BasicParameters extends CommunicationParameters {

	// Classes which extend this will be used to share information between the
	// front and back end
	private MapObject myCalledMe;
	private GameLevelEngine myEngine;

	public BasicParameters(MapObject calledMe, GameLevelEngine engine) {
		this.myCalledMe = calledMe;
		this.myEngine = engine;
	}

	public BasicParameters(GameLevelEngine engine) {
		this.myCalledMe = null;
		this.myEngine = engine;
	}

	public BasicParameters(BasicParameters params, MapObject calledMe) {
		this(calledMe, params.getEngine());
	}

	public BasicParameters(BasicParameters params) {
		this(params, params.getCalledMe());
	}

	public GameLevelEngine getEngine() {
		return this.myEngine;
	}

	public int getTurn() {
		return myEngine.getTurn();
	}

	public MapObject getCalledMe() {
		return myCalledMe;
	}

}
