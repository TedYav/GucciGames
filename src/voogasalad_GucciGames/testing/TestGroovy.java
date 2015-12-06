package voogasalad_GucciGames.testing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import junit.framework.TestCase;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.groovyEngine.AGroovyCustomObject;
import voogasalad_GucciGames.gameEngine.groovyEngine.GameGroovyEngine;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyCustomCharacteristic;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyCustomEvent;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyLoader;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEventHandler;

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

	/*
	 * General outline for how to use event creation.  However, you would need to create a whole 
	 * engine for it to work properly.
	 */
	/*
	public void testEventCreation() throws InstantiationException, IllegalAccessException, 
	NoSuchMethodException, SecurityException, IllegalArgumentException, 
	InvocationTargetException{
		String name = "AttackSelf";
		StringBuilder request = new StringBuilder();
		StringBuilder action = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					this.getClass().getClassLoader().getResource("./resources/testing/attackSelfAction.txt").getFile()));
			br.lines().forEach(line -> {action.append(line + "\n");});
			br = new BufferedReader(new FileReader(
					this.getClass().getClassLoader().getResource("./resources/testing/attackSelfRequest.txt").getFile()));
			br.lines().forEach(line -> {request.append(line + "\n");});
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GroovyCustomEvent g = new GroovyCustomEvent(name,request.toString(),action.toString());
		GameGroovyEngine engine = new GameGroovyEngine();
		List<AGroovyCustomObject> groovyCustom = new ArrayList<>();
		System.out.println("\n" + g.getGroovyString());
		groovyCustom.add(g);
		GroovyLoader loader = engine.createLoader(groovyCustom);
		Constructor<?> ctor = loader.load(name).getDeclaredConstructor(String.class, List.class, List.class);
		MapObjectEvent moe = (MapObjectEvent) ctor.newInstance(null,new ArrayList<>(),new ArrayList<>()); 
		MapObject mo = new MapObject("test","");
		mo.addEvent(name, moe);
	}
*/
}
