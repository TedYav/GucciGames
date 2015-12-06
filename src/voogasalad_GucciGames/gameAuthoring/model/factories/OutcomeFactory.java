package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.InputStream;

public class OutcomeFactory extends AFactory{

	private static final String PATH_TO_Outcome = "outcomesPath.properties";

	@Override
	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_Outcome);
	}


}
