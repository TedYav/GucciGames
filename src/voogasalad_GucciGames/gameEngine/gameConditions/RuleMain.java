package voogasalad_GucciGames.gameEngine.gameConditions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class RuleMain {
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		GamePlayerPerson p0 = new GamePlayerPerson();
		GamePlayerPerson p = new GamePlayerPerson();
		//List<GamePlayerPerson> l = new ArrayList<GamePlayerPerson>();
		//l.add(p0);
		//l.add(p);
		Map<Integer,GamePlayerPerson> m = new TreeMap<>();
		m.put(0, p0);
		m.put(1, p);
		AllPlayers a = new AllPlayers(m);
		ConditionsFactory f = new ConditionsFactory(a);
		List<Integer> i = new ArrayList<Integer>();
		List<Object> s = new ArrayList<Object>();
		i.add(1);
		s.add(i);
		ConditionsCreated c = new ConditionsCreated();
		c = f.createCondition("PlayerUnitCondition", "player", s, c);
		c.loop();
		System.out.println("test1");

	}

}
