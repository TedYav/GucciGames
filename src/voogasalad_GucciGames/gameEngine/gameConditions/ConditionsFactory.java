package voogasalad_GucciGames.gameEngine.gameConditions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameEngine.gameConditions.oucomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al will re-factor this class after testing
 *
 */
public class ConditionsFactory {
	private AllPlayers myPlayers;
	private Outcome myOutcomes;
///resources/gameConditions/conditions.properties
	private static final String PATH_TO_CONDITIONS_PROPERTIES = "voogasalad_GucciGames.resources.gameConditions.conditionPath";
private static final String PATH_TO_OUTCOMES_PROPERTIES = "voogasalad_GucciGames.resources.gameOutcomes.outcomes";
	private ResourceBundle conditionBundle;
	private ResourceBundle outcomeBundle;



	public ConditionsFactory(AllPlayers players) {
		myPlayers = players;
		myOutcomes = new Outcome(players);

		conditionBundle = ResourceBundle.getBundle(PATH_TO_CONDITIONS_PROPERTIES);
		outcomeBundle = ResourceBundle.getBundle(PATH_TO_OUTCOMES_PROPERTIES);


	}

	public ConditionsCreated createCondition(String name, String type, List<Object> args,
			ConditionsCreated createdConditons)
					throws InstantiationException, IllegalAccessException, ClassNotFoundException,
					IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// 1. default vs custom rule
		if (conditionBundle.containsKey(name)) {
			List<GamePlayerPerson> players = new ArrayList<GamePlayerPerson>();

			if (type.equals("player")) {
				if (args != null) {
					@SuppressWarnings("unchecked")
					List<Integer> playerID = (List<Integer>) args.get(0);
					Iterator<Integer> idIterator = playerID.iterator();
					while (idIterator.hasNext()) {
						players.add(myPlayers.getActivePlayer(idIterator.next()));
					}
				}
				// thanks Efe!
				Class<Conditions> condition = (Class<Conditions>) Class.forName(conditionBundle.getString(name));
				// if you pass a list do not need its type. make sure you are
				// not passing an arraylist by mistake
				Constructor<Conditions> condConstructor = condition.getDeclaredConstructor(List.class,
						myOutcomes.getClass());
				Conditions conditionInstance = condConstructor.newInstance(players, myOutcomes);
				createdConditons.addCondition(name, conditionInstance);
			} else {
				// add rules for levels
			}
		} else {
			System.out.println("not found");
			// add custom rules
			// another if then so re-factor above into methods
			// add cond to condproperties file
		}
		return createdConditons;

	}

}
