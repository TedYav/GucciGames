// This entire file is part of my masterpiece.
// Ted Yavuzkurt
package voogasalad.util.cloud.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import voogasalad.util.cloud.server.CloudServer;

public class CloudLoader<T extends CloudObject> {

	private List<CloudParameter> myParameters;
	private List<String> myFields;
	private CloudObject myTemplate;
	private CloudServer myServer;

	public CloudLoader(CloudServer server, Class<T> templateClass) {
		myTemplate = makeTemplate(templateClass);
		myServer = server;
		myParameters = myTemplate.getParameters();
		myFields = myTemplate.getFields();
	}

	public List<T> retrieve() {
		List<Map<String, String>> rawData = myServer.retrieve(myParameters, myFields);
		return parse(rawData);
	}

	@SuppressWarnings("unchecked")
	private List<T> parse(List<Map<String, String>> rawData) {
		return (List<T>) rawData.stream().map((m) -> myTemplate.cloneFromTemplate(m)).collect(Collectors.toList());
	}
	
	private T makeTemplate(Class<T> clazz) {
		T result = null;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
}
