package voogasalad_GucciGames.gameEngine.groovyEngine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GroovyCustomEvent extends AGroovyCustomObject{
	
	private static String BUNDLE_STRING = "resources.groovy.groovyStringsEvents";
	private static String IMPORT_FILE = "./resources/groovy/imports.txt";
	
	private String myGroovyString;
	
	public GroovyCustomEvent(String name, String request, String action) {
		super(name);
		// TODO Auto-generated constructor stub
		String imports = getImports();
		StringBuilder groovy = new StringBuilder();
		
		
		
		groovy.append(imports);
		
		this.myGroovyString = String.format(groovy.toString(), name,name,request,action);
		
		System.out.println(myGroovyString);
	}

	@Override
	public String getGroovyString() {
		// TODO Auto-generated method stub
		return myGroovyString;
	}
	
	private String getImports(){
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(
					this.getClass().getClassLoader().getResource(IMPORT_FILE).getFile()));
			br.lines().forEach(line -> {
				sb.append(line + "\n");
			});
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void main(String[] a){
		new GroovyCustomEvent("","request","action");
	}

	
	
}
