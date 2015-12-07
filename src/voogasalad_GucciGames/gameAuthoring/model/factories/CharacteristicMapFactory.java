package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.InputStream;

public class CharacteristicMapFactory extends DefaultFactory {
	private TypeMap typeMap = new TypeMap();
	private static final String PATH_TO_RULE_PROPERTIES = "MapObjectCharacteristicsPath.properties";

	@Override
	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_RULE_PROPERTIES);
	}

}
