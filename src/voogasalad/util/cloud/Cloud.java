// This entire file is part of my masterpiece.
// Ted Yavuzkurt

package voogasalad.util.cloud;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import voogasalad.util.cloud.config.ConfigLoader;
import voogasalad.util.cloud.data.CloudLoader;
import voogasalad.util.cloud.data.CloudScore;
import voogasalad.util.cloud.data.GameScore;
import voogasalad.util.cloud.exception.CloudException;
import voogasalad.util.cloud.server.CloudServer;
import voogasalad.util.reflection.Reflection;

/**
 * Cloud Utility Dispatcher Class
 * 
 * @author Ted Yavuzkurt
 *
 */
public class Cloud {

	private CloudServer myServer;
	private ResourceBundle myConfig;

	// PLEASE CHANGE THIS IF YOU MOVE THIS CLASS SOMEWHERE ELSE
	private static final String CONFIGROOT = "voogasalad.util.cloud.config.";

	/**
	 * EXAMPLE: Cloud c = new Cloud(); c.retrieveHighScores("GameName");
	 */

	public Cloud() {
		ConfigLoader.setPrefix(CONFIGROOT);
		myConfig = ConfigLoader.mainConfig();
		if (myConfig.getString("GroupName").isEmpty()) {
			throw new CloudException("ERROR: Please put your group name in CloudConfig.properties!");
		}
		myServer = (CloudServer) Reflection.createInstance(ConfigLoader.internalConfig().getString("ServerClass"));
	}

	/**
	 * Adds a new high score to the cloud.
	 * 
	 * @param gameName
	 * @param playerName
	 *            - pass empty string if your game does not use players
	 * @param score
	 * @throws CloudException
	 */
	public void addHighScore(String gameName, String playerName, double score) throws CloudException {
		new CloudScore(gameName, playerName, score).upload(myServer);
	}

	/**
	 * Returns a List<GameScore> of PlayerNames to HighScores Call
	 * getPlayerName(), getScore(), and getTitle() to get information about the
	 * scores.
	 * 
	 * @param gameName
	 *            - name of the game to query
	 * @return
	 * @throws CloudException
	 */
	public List<GameScore> retrieveHighScores(String gameName) throws CloudException {
		CloudLoader<CloudScore> loader = new CloudLoader<>(myServer, CloudScore.class);
		return Collections.unmodifiableList(loader.retrieve());
	}

}
