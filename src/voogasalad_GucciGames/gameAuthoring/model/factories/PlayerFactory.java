
package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.InputStream;

import voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser.TypeMap;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerFactory extends DefaultFactory {

	private TypeMap typeMap = new TypeMap();
	private static final String PATH_TO_PLAYER_CHARS = "playerCharsPath.properties";

	@Override
	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_PLAYER_CHARS);

	}

}
