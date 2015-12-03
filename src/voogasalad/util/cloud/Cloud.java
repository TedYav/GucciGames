package voogasalad.util.cloud;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

import voogasalad.util.cloud.config.ConfigLoader;
import voogasalad.util.cloud.data.CloudLoader;
import voogasalad.util.cloud.data.CloudScore;
import voogasalad.util.cloud.exception.CloudException;
import voogasalad.util.cloud.server.BasicPHPServer;
import voogasalad.util.cloud.server.CloudServer;

/**
 * Cloud Utility Dispatcher Class
 * @author Ted Yavuzkurt
 *
 */
public class Cloud {
	
	//private CloudUploader myQueue;
	private CloudServer myServer;
	private ResourceBundle myConfig;
	
	// PLEASE CHANGE THIS IF YOU MOVE THIS CLASS SOMEWHERE ELSE
	private static final String CONFIGROOT = "voogasalad.util.cloud.config.";
	
	/**
	 * EXAMPLE:
	 * Cloud c = new CLoud();
	 * c.retrieveHighScores("GameName");
	 */
	
	public Cloud(){
		ConfigLoader.setPrefix(CONFIGROOT);
		myConfig = ConfigLoader.mainConfig();
		if(myConfig.getString("GroupName").isEmpty()){
			throw new CloudException("ERROR: Please put your group name in CloudConfig.properties!");
		}
		myServer = new BasicPHPServer();
	}
	
//	/**
//	 * Saves the named game to the cloud.
//	 * If the game already exists on the server, it will overwrite the old copy.
//	 * 
//	 * NOTE: use a File Chooser to ensure that files are uploaded successfully
//	 * 
//	 * @param gameName
//	 * @param filenames
//	 */
//	public void saveGameData(String gameName, List<String> filenames) throws CloudException{
//		System.err.println("this function hasn't been implemented yet, sorry :(");
//	}
//	
//	/**
//	 * Retrieves the specified game from the cloud, returns a List<String> of files.
//	 * @param gameName
//	 * @param targetDirectory
//	 * @return List<String> of files.
//	 */
//	public List<String> retrieveGameData(String gameName) throws CloudException{
//		System.err.println("this function hasn't been implemented yet, sorry :(");
//		return null;
//	}
//	
//	public List<String> retrieveGames() throws CloudException{
//		System.err.println("this function hasn't been implemented yet, sorry :(");
//		return null;
//	}
//	
//	public void saveGameState(String gameName, String playerName, String saveName, List<String> filenames) throws CloudException{
//		System.err.println("this function hasn't been implemented yet, sorry :(");
//	}
//	
//	public List<String> retrieveGameState(String gameName, String playerName, String saveName) throws CloudException{
//		System.err.println("this function hasn't been implemented yet, sorry :(");
//		return null;
//	}
//	
//	public List<String> retrieveGameStates(String gameName, String playerName) throws CloudException{
//		System.err.println("this function hasn't been implemented yet, sorry :(");
//		return null;
//	}
	
	/**
	 * Adds a new high score to the cloud.
	 * @param gameName
	 * @param playerName - leave blank if your game does not use players
	 * @param score
	 * @throws CloudException
	 */
	public void addHighScore(String gameName, String playerName, double score) throws CloudException{
		new CloudScore(gameName, playerName, score).upload(myServer);;
	}
	
	/**
	 * Returns a treemap of PlayerNames to HighScores
	 * @param gameName - name of the game to query
	 * @return
	 * @throws CloudException
	 */
	// TODO: change this so it's not a tree map
	public TreeMap<String, Double> retrieveHighScores(String gameName) throws CloudException{
		CloudLoader<CloudScore> loader = new CloudLoader<>(new CloudScore(gameName), myServer);
		TreeMap<String, Double> scoreList = new TreeMap<>();
		loader.retrieve().forEach((c) -> scoreList.put(c.getPlayerName(), c.getScore()));
		return scoreList;
	}
	
	public void clearHighScores(String gameName) throws CloudException{
		
	}
	
}
