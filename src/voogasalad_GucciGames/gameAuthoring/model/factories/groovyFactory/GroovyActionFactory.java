package voogasalad_GucciGames.gameAuthoring.model.factories.groovyFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameEngine.groovyEngine.AGroovyCustomObject;
import voogasalad_GucciGames.gameEngine.groovyEngine.GameGroovyEngine;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyCustomEvent;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyLoader;

/**
 *
 * @author Sally Al
 *
 */
public class GroovyActionFactory {

	public Object create(Map<String, GActionParams> actionParams, ActionParamsValue actionValues)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {


		GActionParams param = actionParams.get(actionValues.getName());
		String name = param.getName();
	         List<AGroovyCustomObject> groovyCustom = new ArrayList<>();
		groovyCustom.add(new GroovyCustomEvent(name, param.getRequest(), param.getAction()));
	        GroovyLoader loader = new GameGroovyEngine().createLoader(groovyCustom);
		Constructor<?> c = loader.load(name).getDeclaredConstructor();
		Object myObject = c.newInstance();

		return myObject;

	}
}
