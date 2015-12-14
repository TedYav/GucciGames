// This entire file is part of my masterpiece.
// Ted Yavuzkurt

package voogasalad.util.cloud.server;

import java.util.List;
import java.util.Map;

import voogasalad.util.cloud.data.CloudParameter;
import voogasalad.util.cloud.exception.CloudException;

public interface CloudServer {

	public int initializeID() throws CloudException;

	public void upload(List<CloudParameter> parameters);

	public List<Map<String, String>> retrieve(List<CloudParameter> parameters, List<String> values);

}
