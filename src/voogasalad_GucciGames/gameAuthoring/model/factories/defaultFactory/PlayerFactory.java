
package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.InputStream;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerFactory extends Leaf  {

	private static final String PATH_TO_PLAYER_CHARS = "playerCharsPath.properties";


	@Override
	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_PLAYER_CHARS);

	}

}
