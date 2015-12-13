package voogasalad.util.cloud.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import voogasalad.util.cloud.exception.CloudException;
import voogasalad.util.cloud.server.CloudServer;

public abstract class CloudObject {

	private List<CloudParameter> myParameters;
	private String myGameName;

	public CloudObject(String gameName) {
		myParameters = new ArrayList<>();
		myParameters.add(this.requestString());
		myGameName = gameName;
	}

	public String getGameName() {
		return myGameName;
	}

	public void setParameter(String name, String value) {
		myParameters.add(new CloudParameter(name, value));
	}

	public void upload(CloudServer server) throws CloudException {
		server.upload(myParameters);
	}

	public abstract CloudParameter requestString();

	public List<CloudParameter> getParameters() {
		return myParameters.stream().filter((p) -> !p.getValue().isEmpty()).collect(Collectors.toList());
	}

	public abstract CloudObject cloneFromTemplate(Map<String, String> template);

	public abstract List<String> getFields();
}
