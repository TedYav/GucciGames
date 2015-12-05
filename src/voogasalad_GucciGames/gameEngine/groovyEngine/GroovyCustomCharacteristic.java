package voogasalad_GucciGames.gameEngine.groovyEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import groovy.lang.GroovyClassLoader;

public class GroovyCustomCharacteristic extends AGroovyCustomObject{

	private static final String 
		IMPORT = "IMPORT",
		INIT = "INIT",
		CONSTRUCTOR = "CONSTRUCTOR",
		DECLARE = "DECLARE",
		MAP_OBJECT_HEADING = "MAP_OBJECT_HEADING",
		GET="GET",
		SET="SET";
	
	private String myGroovyString;
	
	private String BUNDLE_STRING = "resources.groovy.groovyStringsMapObjectCharacteristic";
	
	public GroovyCustomCharacteristic(String name, Map<String,String> typeToVariable) {
		super(name);
		// TODO Auto-generated constructor stub
		ResourceBundle resource = ResourceBundle.getBundle(BUNDLE_STRING);
		StringBuilder groovyString = new StringBuilder();
		StringBuilder init = new StringBuilder();
		StringBuilder constructorParams = new StringBuilder();
		StringBuilder declaration = new StringBuilder();
		StringBuilder getters = new StringBuilder();
		StringBuilder setters = new StringBuilder();
		for(String type: typeToVariable.keySet()){
			String var = typeToVariable.get(type);
			constructorParams.append(type + " " + var + ",");
			declaration.append(String.format(resource.getString(DECLARE),type,var));
			init.append(String.format(resource.getString(INIT),var,var));
			getters.append(String.format(resource.getString(GET),
					type,var,var));
			setters.append(String.format(resource.getString(SET),
				 var,type,var));
		}
		
		StringBuilder code = new StringBuilder();
		code.append(declaration.toString());
		code.append(String.format(resource.getString(CONSTRUCTOR), name,constructorParams.toString().substring(0, constructorParams.length()-1),init.toString()));
		code.append(getters.toString());
		code.append(setters.toString());
		
		groovyString.append(resource.getString(IMPORT));
		groovyString.append(
				String.format(resource.getString(MAP_OBJECT_HEADING),name,code.toString()));
		//System.out.println(groovyString);
		myGroovyString = groovyString.toString();
	}

	@Override
	public String getGroovyString(){
		return this.myGroovyString;
	}
	
	/*
	public static void main(String[] a){
		Map<String,String> map = new TreeMap<>();
		map.put("double", "health");
		map.put("int", "death");
		GroovyCustomCharacteristic g = new GroovyCustomCharacteristic("health",map);
	}
	*/

}
