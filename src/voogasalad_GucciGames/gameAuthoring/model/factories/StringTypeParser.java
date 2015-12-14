package voogasalad_GucciGames.gameAuthoring.model.factories;

public class StringTypeParser {
	public static final String INTEGER = "int";
	public static final String DOUBLE = "double";
	public static final String STRING = "String";
	public static final String BOOLEAN = "boolean";


	public Object getTranslatedValue(String type, String value) throws TypeParseExeption {
		if (type.equals(INTEGER))
			return Integer.parseInt(value);
		if (type.equals(DOUBLE))
			return Double.parseDouble(value);
		if (type.equals(STRING))
			return value;
		if (type.equals(BOOLEAN))
			return Boolean.parseBoolean(value);
		else
			throw new TypeParseExeption("Failed to parse type name");
	}

}
