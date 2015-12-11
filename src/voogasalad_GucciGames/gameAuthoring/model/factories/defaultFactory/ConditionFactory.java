package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.InputStream;

/**
 *
 * @author Sally Al
 *
 */
public class ConditionFactory extends  Leaf {

	private static final String PATH_TO_Condition = "conditionsPath.properties";

	@Override
	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_Condition);
	}


}
