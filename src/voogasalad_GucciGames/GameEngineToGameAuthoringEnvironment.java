package voogasalad_GucciGames;

import voogasalad_GucciGames.gameAuthoring.model.GameSourceData;

public interface GameEngineToGameAuthoringEnvironment {

//Front end team; write whatever classes you need in that interface	
	
    public boolean checkForCompileErrors(GameSourceData data);

}
