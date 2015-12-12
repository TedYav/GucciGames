package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory.TypeName;

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
		map.put(TypeName.INT.getValue(), int.class);
		map.put(TypeName.STRING.getValue(), String.class);
		map.put(TypeName.DOUBLE.getValue(), double.class);
		map.put(TypeName.BOOLEAN.getValue(), boolean.class);
		Collections.unmodifiableMap(map);
	}

	public static Class<?> getType(String name) {
		return map.get(name);
	}

}
