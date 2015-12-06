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
		myMap.put(name, super.parseClass(groovyCode));
	}
	
	public Class<?> load(String name){
		return myMap.get(name);
	}

}
