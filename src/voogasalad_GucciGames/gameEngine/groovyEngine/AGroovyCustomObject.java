package voogasalad_GucciGames.gameEngine.groovyEngine;

import org.codehaus.groovy.ant.Groovy;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;

public abstract class AGroovyCustomObject {
	
	protected String myName;
	
	public AGroovyCustomObject(String name){
		this.myName = name;
	}
	
	public String getName(){
		return this.myName;
	}
	
	public abstract String getGroovyString(); 
	
}
