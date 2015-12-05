package voogasalad_GucciGames.gameEngine.groovyEngine;

import java.util.HashMap;
import java.util.Map;

import groovy.lang.GroovyClassLoader;

public class GroovyLoader extends GroovyClassLoader{
	
	private Map<String,Class<?>> myMap;
	
	public GroovyLoader(){
		this.myMap = new HashMap<>();
	}
	
	public void add(String name, String groovyCode){
		try {
			myMap.put(name, super.loadClass(groovyCode));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Class<?> load(String name){
		return myMap.get(name);
	}

}
