// This entire file is part of my masterpiece.
// Daniel McKee

package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.InputStream;

import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;

/**
 *
 * @author Sally Al and Daniel McKee
 *
 */
public class PlayerCharacteristicFactory extends AFactory<APlayerChars> {

	private static final String PATH_TO_PLAYER_CHARS = "playerCharsPath.properties";

	@Override
	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_PLAYER_CHARS);

	}

}
