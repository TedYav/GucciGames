package voogasalad_GucciGames.gameData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GameInfoToGameData;

public class GameDataManager implements GameDataInterface {

	private XStreamGameEngine myXStream;
	private GameListManager myGameList;
	private GameFileHelper myFileHelper;
	
    private final ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GameData");

    private String myBasePath;
    
	public GameDataManager(){
		myXStream = new XStreamGameEngine();
		myGameList = new GameListManager();
		myFileHelper = new GameFileHelper();
		myBasePath = myConfig.getString("BaseResourcePath");
	}

	@Override
	public GameInfo loadGame(String name) {
		return myXStream.loadGameByName(name);
	}

	@Override
	public List<String> getAvailableGames() {
		return myGameList.listGames();	
	}

	@Override
	public GameInfo loadGameFromFile(String path) throws GameDataException {
		if(!path.endsWith(myConfig.getString("GameExtension")))
			throw new GameDataException("Please select a valid game data file.)");
		return myXStream.loadGameInfo(path);
	}

	@Override
	public GameInfo loadDefault() {
		return myXStream.loadGameByName(myGameList.listGames().get(0));
	}
	
	public String getGamePath(GameInfoToGameData myGame){
		return myXStream.gameNameToFileName(myGame.getGameName());
	}

	public List<String> getResources(List<String> extensions) {
		return myFileHelper.getMatchingFiles(myBasePath, extensions);
	}
	
	public List<String> getResources(List<String> extensions, String path){
		return myFileHelper.getMatchingFiles(myBasePath + "/" + path, extensions);
	}

	public void copyResourceToGame(String URI, GameInfoToGameData game) {
		myFileHelper.copyResource(myBasePath + URI, getGamePath(game)+ "/" +myConfig.getString("ResourcesPath"));
	}

}
