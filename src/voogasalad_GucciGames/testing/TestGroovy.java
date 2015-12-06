package voogasalad_GucciGames.testing;

import junit.framework.TestCase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import voogasalad_GucciGames.gameEngine.groovyEngine.AGroovyCustomObject;
import voogasalad_GucciGames.gameEngine.groovyEngine.GameGroovyEngine;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyCustomCharacteristic;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyLoader;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class TestGroovy extends TestCase{

	public void testCharacteristicCreation() throws InstantiationException, IllegalAccessException,
	NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException,
	ScriptException, ClassNotFoundException{
		Map<String,String> map = new TreeMap<>();
		map.put("health", "Double");
		map.put("regenerate", "Double");
		GroovyCustomCharacteristic g = new GroovyCustomCharacteristic("SuperHealth",map);
		GameGroovyEngine engine = new GameGroovyEngine();
		List<AGroovyCustomObject> groovyCustom = new ArrayList<>();
		System.out.println(g.getGroovyString());
		groovyCustom.add(g);
		GroovyLoader loader = engine.createLoader(groovyCustom);
		Constructor<?> ctor = loader.load("SuperHealth").getDeclaredConstructor(Double.class,Double.class);
		Object superHealth = ctor.newInstance(5.0,5.0);
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine groovy = manager.getEngineByName("groovy");
		groovy.put("superHealth", superHealth);
		String code = "System.out.println(superHealth.gethealth());"
				+ "println superHealth.getregenerate();"
				+ "superHealth.setregenerate(10);"
				+ "println superHealth.getregenerate();"
				+ "superHealth.sethealth(10);";
		groovy.eval(code);
		double health = (double) groovy.eval("superHealth.gethealth();");
		assertEquals("Health is 10: ",health,(double) 10);
	}

	

}
