package voogasalad_GucciGames.gameEngine.gameConditions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al will re-factor this class after testing
 *
 */
public class ConditionsFactory {
	private static final String PATH_TO_CONDITIONS_PROPERTIES = "conditionPath.properties";
	private static final String PATH_TO_OUTCOMES_PROPERTIES = "voogasalad_GucciGames.resources.gameOutcomes.outcomes";

	private ResourceBundle outcomeBundle;
	private InputStream inputStream;

	public ConditionsFactory() {
		// conditionBundle =
		// ResourceBundle.getBundle(PATH_TO_CONDITIONS_PROPERTIES);
		// outcomeBundle =
		// ResourceBundle.getBundle(PATH_TO_OUTCOMES_PROPERTIES);

	}

	public Conditions createCondition(ConditionParams condParams, BasicParameters params)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, IOException {

		// 1. default vs custom rule
		inputStream = getClass().getResourceAsStream(PATH_TO_CONDITIONS_PROPERTIES);
		Properties prop = new Properties();
		prop.load(inputStream);

		if (prop.containsKey(condParams.getName().toString())) {
			List<GamePlayerPerson> players = new ArrayList<GamePlayerPerson>();
			if (condParams.getType().equals("player")) {
				@SuppressWarnings("unchecked")
				Iterator<Integer> idIterator = condParams.getPlayerID().iterator();
				while (idIterator.hasNext()) {
					players.add(params.getEngine().getPlayers().getPlayerById(idIterator.next()));

				}
				condParams.setMyPlayers(players);

				// thanks Efe!
				Class<Conditions> condition = (Class<Conditions>) Class.forName(prop.getProperty(condParams.getName()));

				Constructor<Conditions> condConstructor = condition.getDeclaredConstructor(ConditionParams.class,
						BasicParameters.class);

				Conditions conditionInstance = condConstructor.newInstance(condParams, params);
				return conditionInstance;

			} else {
				System.out.println("cond for level");
			}
		} else {
			System.out.println("not found");
		}
		return null;

	}

}
