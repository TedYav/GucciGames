// This entire file is part of my masterpiece.
// Daniel McKee

package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.InputStream;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class MapCharacteristicFactory extends AFactory<AMapObjectCharacteristic> {
	private static final String PATH_TO_MAP_CHARACTERISTIC_PROPERTIES = "MapObjectCharacteristicsPath.properties";

	@Override
	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_MAP_CHARACTERISTIC_PROPERTIES);
	}

}
