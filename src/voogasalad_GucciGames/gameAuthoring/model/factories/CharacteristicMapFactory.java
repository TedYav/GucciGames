package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.InputStream;

import voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser.TypeMap;

public class CharacteristicMapFactory extends DefaultFactory {
	private TypeMap typeMap = new TypeMap();
	private static final String PATH_TO_RULE_PROPERTIES = "MapObjectCharacteristicsPath.properties";

	@Override
	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_RULE_PROPERTIES);
	}

}
