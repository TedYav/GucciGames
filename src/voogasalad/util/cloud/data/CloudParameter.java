package voogasalad.util.cloud.data;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class CloudParameter {

	private String myName;
	private String myValue;
	
	public CloudParameter(String name, String value){
		myName = name;
		myValue = value;
	}
	
	public String getName(){
		return myName;
	}
	
	public String getValue(){
		return myValue;
	}
	
	public <T> T getRequest(Function<List<String>, T> converter){
		return converter.apply(Arrays.asList(myName, myValue));
	}
}
