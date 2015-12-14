package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.InputStream;

import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;

/**
 *
 * @author Sally Al
 *
 */
public class ConditionFactory extends AFactory<Conditions> {

	private static final String PATH_TO_Condition = "conditionsPath.properties";
	InputStream inputStream;

	@Override
	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_Condition);
	}

}
