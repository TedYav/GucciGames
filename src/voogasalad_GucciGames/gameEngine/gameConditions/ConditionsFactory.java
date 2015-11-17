package voogasalad_GucciGames.gameEngine.gameConditions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al will re-factor this class after testing
 *
 */
public class ConditionsFactory {
	private static final String PATH_TO_CONDITIONS_PROPERTIES = "voogasalad_GucciGames.resources.gameConditions.conditionPath";
	private static final String PATH_TO_OUTCOMES_PROPERTIES = "voogasalad_GucciGames.resources.gameOutcomes.outcomes";
	private ResourceBundle conditionBundle;
	private ResourceBundle outcomeBundle;



	public ConditionsFactory() {
		conditionBundle = ResourceBundle.getBundle(PATH_TO_CONDITIONS_PROPERTIES);
		outcomeBundle = ResourceBundle.getBundle(PATH_TO_OUTCOMES_PROPERTIES);


	}

	public CommunicationParams createCondition(ConditionParams condParams, CommunicationParams params)
					throws InstantiationException, IllegalAccessException, ClassNotFoundException,
					IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// 1. default vs custom rule
		if (conditionBundle.containsKey(condParams.getName())) {
			List<GamePlayerPerson> players = new ArrayList<GamePlayerPerson>();
			if (condParams.getType().equals("player")) {
				if (condParams.getArgs() != null) {
					@SuppressWarnings("unchecked")
					List<Integer> playerID = (List<Integer>) condParams.getArgs().get(0);
					Iterator<Integer> idIterator = playerID.iterator();
					while (idIterator.hasNext()) {
						players.add(params.getPlayers().getPlayerById(idIterator.next()));
					}
				}
				// thanks Efe!
				Class<Conditions> condition = (Class<Conditions>) Class.forName(conditionBundle.getString(condParams.getName()));
				Constructor<Conditions> condConstructor = condition.getDeclaredConstructor(List.class,CommunicationParams.class );
				Conditions conditionInstance = condConstructor.newInstance(players, params);
				//params.getcreatedConditons().addCondition(condParams.getName(), conditionInstance);
			} else {
				System.out.println("cond for level");
			}
		} else {
			System.out.println("not found");
		}
		return params;

	}

}
