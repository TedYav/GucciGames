package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.player;

import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionParams;
import voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.DefaultConditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class PlayerConditions extends DefaultConditions {
	private List<GamePlayerPerson> myPlayerList;
	private ConditionParams myCondParams;
	public PlayerConditions(ConditionParams condParams, BasicParameters params) {
		super(condParams, params);
		myPlayerList=condParams.getMyPlayers();
		myCondParams = condParams;
	}

	@Override
	public void execute(BasicParameters params) {
		System.out.println("number of players affected: "+myPlayerList.size());
		System.out.println(myPlayerList);
		
		for(int i = 0; i < myPlayerList.size(); i++){
			apply(myPlayerList.get(i),  params);
		}
		
		
		Iterator<GamePlayerPerson> playersIterator = myPlayerList.iterator();
		while(playersIterator.hasNext()){
			apply(playersIterator.next(),  params);
		}
		if(myCondParams.removeIDSize()>0){
			myCondParams.removePlayer();
		}
	}
	protected abstract void apply(GamePlayerPerson player , BasicParameters params);
}
