package voogasalad_GucciGames.gameEngine.networking;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

public class GameEngineNullRole extends GameNetworkRole {

	public GameEngineNullRole(GameNetworkEngineInterface engine) {
		super(engine);
//do nothing 
		}

	@Override
	public void run() {
		//do nothing

	}

	@Override
	public GameNetworkRoleType getRoleType() {
		// TODO Auto-generated method stub
		return GameNetworkRoleType.NULL;
	}

}
