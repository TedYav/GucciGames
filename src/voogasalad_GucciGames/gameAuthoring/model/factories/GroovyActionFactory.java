package voogasalad_GucciGames.gameAuthoring.model.factories;

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
	// outline vs instance which has values.
	// add default rules to action

	public Object create(Map<String, GActionParams> actionParams, ActionParamsValue actionValues)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// param name to type
		// groovycustevent instance to loader.

		// create constructor;
		// this creates the loader every time and adds the specific char ying
		// send to us with the value thing and send it back
		// save it in a list
		GActionParams param = actionParams.get(actionValues.getName());
		GroovyCustomEvent g = new GroovyCustomEvent(param.getName(), param.getRequest(), param.getAction());
		GameGroovyEngine engine = new GameGroovyEngine();
		List<AGroovyCustomObject> groovyCustom = new ArrayList<>();
		groovyCustom.add(g);
		GroovyLoader loader = engine.createLoader(groovyCustom);
		Constructor<?> c = loader.load(param.getName()).getDeclaredConstructor();
		Object myObject = c.newInstance();
		System.out.println(myObject.getClass().toString());

		return myObject;

	}
}
