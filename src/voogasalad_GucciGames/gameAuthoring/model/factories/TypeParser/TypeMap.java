package voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sally Al
 *
 */
public class TypeMap {
	// string to class.
	private static Map<String, Class> map;

	public TypeMap() {
		map = new HashMap<String, Class>();
		map.put("int", int.class);
		map.put("String", String.class);
		map.put("double", double.class);
		map.put("Boolean", boolean.class);
		Collections.unmodifiableMap(map);
	}

	public static Class getType(String name) {
		return map.get(name);
	}

}
