package voogasalad_GucciGames.gameEngine.gameRule;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRule.oucomes.Outcome;

/**
 *
 * @author Sally Al will re-factor this class after testing
 *
 */
public class ConditionsFactory {
	private AllPlayers myPlayers;
	private Outcome myOutcomes;
	private final String CONDITIONS_PROPERTIES = "resources/conditions";
	private final String OUTCOMES_PROPERTIES = "resources/outcomes";
	private ResourceBundle conditionBudle;
	private ResourceBundle outcomeBundle;
	private HackyCondRes conditionMap;

	public ConditionsFactory(AllPlayers players) {
		myPlayers = players;
		myOutcomes = new Outcome(players);
		conditionBudle = ResourceBundle.getBundle(CONDITIONS_PROPERTIES);
		outcomeBundle = ResourceBundle.getBundle(OUTCOMES_PROPERTIES);
		conditionMap = new HackyCondRes();
	}

	public void createCondition(String name, String type, List<?> args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		// 1. default vs custom rule
		if (conditionMap.containsKey(name)) {
			List<GamePlayerPerson> players = new ArrayList<GamePlayerPerson>();

			if (type.equals("player")) {
				if (args != null) {
					List<Integer> playerID = (List<Integer>) args.get(0);
					Iterator<Integer> idIterator = playerID.iterator();
					while (idIterator.hasNext()) {
						players.add(myPlayers.getActivePlayer(idIterator.next()));
					}
				}
				// thanks Efe!
				Class<Conditions> condition = (Class<Conditions>) Class.forName(name);
				Constructor<Conditions> condConstructor = condition.getConstructor(players.getClass(),
						myOutcomes.getClass());
				Conditions conditionInstance = condConstructor.newInstance(players, myOutcomes);
				conditionInstance.addRule(conditionInstance);
			} else {
				// add rules for levels
			}
		} else {
			// add custom rules
			// another if then so re-factor above into methods
		}

	}

}
