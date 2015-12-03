package groovy;

import org.codehaus.groovy.ant.Groovy;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;

public class GroovyTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException{
	
		GroovyClassLoader loader = new GroovyClassLoader();
		
		
	 	Class myAbstractClass  = loader.parseClass("public class NoneAlive extends groovy.SampleAbstractClass { public NoneAlive(String ss, Double ddd){}; public String myString(){return 'NONE ALIVE';    }     }");
	 	
	 	Object a = myAbstractClass.newInstance();
		System.out.println(a.getClass().getSimpleName());

	//	System.out.println(myAbstractClass.getConstructors());
	//	System.out.println(((SampleAbstractClass) a).myString());
		
		
				
		
	}
}
