package voogasalad.util.cloud.server;

import java.util.List;
import java.util.Map;

import voogasalad.util.cloud.data.CloudParameter;
import voogasalad.util.cloud.exception.CloudException;

public interface CloudServer {

	void upload(List<CloudParameter> parameters, String filename) throws CloudException;

	void upload(List<CloudParameter> parameters);

	String getRequestString(List<CloudParameter> parameters);

	int initializeID() throws CloudException;

	List<Map<String, String>> retrieve(List<CloudParameter> parameters, List<String> values);

}
