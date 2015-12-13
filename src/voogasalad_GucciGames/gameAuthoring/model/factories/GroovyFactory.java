package voogasalad_GucciGames.gameAuthoring.model.factories;

/**
 *
 * @author Sally Al
 *
 */
public abstract class GroovyFactory {

	protected Object getTranslatedValue(String type, String value) {
		if (type.equals("int"))
			return Integer.parseInt(value);
		if (type.equals("double"))
			return Double.parseDouble(value);
		if (type.equals("String"))
			return value;
		if (type.equals("boolean"))
			return Boolean.parseBoolean(value);
		return null;

	}

}
