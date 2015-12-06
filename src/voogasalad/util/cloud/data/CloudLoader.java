package voogasalad.util.cloud.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import voogasalad.util.cloud.server.CloudServer;

public class CloudLoader<T extends CloudObject> {

	private List<CloudParameter> myParameters;
	private List<String> myFields;
	private CloudObject myTemplate;
	private CloudServer myServer;
	
	public CloudLoader(CloudObject template, CloudServer server){
		myTemplate = template;
		myServer = server;
		myParameters = myTemplate.getParameters();
		myFields = myTemplate.getFields();
		
	}
	
	public List<T> retrieve(){
		List<Map<String,String>> rawData = myServer.retrieve(myParameters, myFields);
		return parse(rawData);
	}

	@SuppressWarnings("unchecked")
	private List<T> parse(List<Map<String, String>> rawData) {
		return (List<T>) rawData.stream()
		.map((m) -> myTemplate.cloneFromTemplate(m))
		.collect(Collectors.toList());
	}
}
