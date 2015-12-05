package voogasalad_GucciGames.gameEngine.groovyEngine;

import org.codehaus.groovy.ant.Groovy;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;

public abstract class AGroovyCustomObject {
	
	private String myName;
	
	public AGroovyCustomObject(String name){
		this.myName = name;
	}
	
	public abstract GroovyClassLoader createLoader(); 
	
}
