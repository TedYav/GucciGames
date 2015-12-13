package voogasalad_GucciGames.gameEngine.groovyEngine;

import java.util.Map;
import java.util.ResourceBundle;

public class GroovyCustomCharacteristic extends AGroovyCustomObject {

	private static final String IMPORT = "IMPORT", INIT = "INIT", CONSTRUCTOR = "CONSTRUCTOR", DECLARE = "DECLARE",
			MAP_OBJECT_HEADING = "MAP_OBJECT_HEADING", GET = "GET", SET = "SET";

	private String myGroovyString;
	private Map<String, String> myVariableToType;

	private String BUNDLE_STRING = "resources.groovy.groovyStringsMapObjectCharacteristic";

	public GroovyCustomCharacteristic(String name, Map<String, String> variableToType) {
		super(name);
		// TODO Auto-generated constructor stub
		this.myVariableToType = variableToType;
		ResourceBundle resource = ResourceBundle.getBundle(BUNDLE_STRING);
		StringBuilder groovyString = new StringBuilder();
		StringBuilder init = new StringBuilder();
		StringBuilder constructorParams = new StringBuilder();
		StringBuilder declaration = new StringBuilder();
		StringBuilder getters = new StringBuilder();
		StringBuilder setters = new StringBuilder();
		for (String var : variableToType.keySet()) {
			String type = variableToType.get(var);
			constructorParams.append(type + " " + var + ",");
			declaration.append(declarationStr(type, var, resource));
			init.append(initStr(type, var, resource));
			getters.append(getter(type, var, resource));
			setters.append(setter(type, var, resource));
		}

		StringBuilder code = new StringBuilder();
		code.append(declaration.toString());
		code.append(String.format(resource.getString(CONSTRUCTOR), name,
				constructorParams.toString().substring(0, constructorParams.length() - 1), init.toString()));
		code.append(getters.toString());
		code.append(setters.toString());

		groovyString.append(resource.getString(IMPORT));
		groovyString.append(String.format(resource.getString(MAP_OBJECT_HEADING), name, code.toString()));
		myGroovyString = groovyString.toString();
	}

	private String declarationStr(String type, String var, ResourceBundle resource) {
		return String.format(resource.getString(DECLARE), type, var);
	}

	private String initStr(String type, String var, ResourceBundle resource) {
		return String.format(resource.getString(INIT), var, var);
	}

	private String getter(String type, String var, ResourceBundle resource) {
		return String.format(resource.getString(GET), type, var, var);
	}

	private String setter(String type, String var, ResourceBundle resource) {
		return String.format(resource.getString(SET), var, type, var);
	}

	@Override
	public String getGroovyString() {
		return this.myGroovyString;
	}

	public Map<String, String> getVariableToType() {
		return this.myVariableToType;
	}

	/*
	 * public static void main(String[] a){ Map<String,String> map = new
	 * TreeMap<>(); map.put("double", "health"); map.put("int", "death");
	 * GroovyCustomCharacteristic g = new
	 * GroovyCustomCharacteristic("health",map); }
	 */

}
