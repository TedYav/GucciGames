package groovy;

import groovy.lang.GroovyClassLoader;

public class GroovyTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException{

		GroovyClassLoader loader = new GroovyClassLoader();


	 	Class myAbstractClass  = loader.parseClass("public class NoneAlive extends groovy.SampleAbstractClass { public NoneAlive(String ss, Double ddd){}; public String myString(){return 'NONE ALIVE';    }     }");

	 	Object a = myAbstractClass.newInstance();

	}
}
