package voogasalad.util.cloud.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;

import voogasalad.util.cloud.config.ConfigLoader;
import voogasalad.util.cloud.data.CloudParameter;
import voogasalad.util.cloud.data.CloudVariable;
import voogasalad.util.cloud.exception.CloudException;

public class BasicPHPServer implements CloudServer {

	private ResourceBundle myGlobalConfig;
	private ResourceBundle myConfig;
	private String myServerAddress;
	private String myServerTarget;
	private int myID;

	// MOVE TO RESOURCE BUNDLE:
	private String entryDivider = "~\n";

	public BasicPHPServer() throws CloudException {
		myGlobalConfig = ConfigLoader.mainConfig();
		myConfig = ConfigLoader.internalConfig();
		myServerAddress = myConfig.getString("ServerAddress");
		myServerTarget = myConfig.getString("PHPTarget");
		myID = initializeID();
	}

	@Override
	public int initializeID() {
		if (myGlobalConfig.getString("GroupID").isEmpty()) {
			int id = requestID(myGlobalConfig.getString("GroupName"));
			System.err.println("PLEASE UPDATE YOUR CloudConfig.properties File with ID#: " + id);
			return id;
		} else {
			return Integer.parseInt(myGlobalConfig.getString("GroupID"));
		}
	}

	private int requestID(String name) throws CloudException {
		List<CloudParameter> myParameters = new ArrayList<>();
		myParameters.add(new CloudParameter(CloudVariable.ACTION, CloudVariable.REGISTER));
		myParameters.add(new CloudParameter(CloudVariable.PROCESS, CloudVariable.ADD));
		myParameters.add(new CloudParameter(CloudVariable.NAME, name));
		String result = request(getRequestString(myParameters));
		return Integer.parseInt(parseResult(result, Arrays.asList("ID")).get(0).get("ID"));
	}

	private List<Map<String, String>> parseResult(String rawdata, List<String> values) {
		List<Map<String, String>> result = Arrays.asList(rawdata.split(entryDivider)).stream()
				.map(s -> parseValue(s, values)).filter(m -> !m.isEmpty()).collect(Collectors.toList());
		if (result.isEmpty()) {
			System.err.println("ERROR: problem with server " + rawdata);
			throw new CloudException("Server error");
		}
		return result;
	}

	private Map<String, String> parseValue(String rawValue, List<String> values) {
		return Arrays.asList(rawValue.split("\n")).stream().filter((s) -> values.contains(s.split("=")[0]))
				.collect(Collectors.toMap((s) -> s.split("=")[0], (s) -> s.split("=")[1]));
	}

	private String getRequestString(List<CloudParameter> parameters) {
		return buildRequestString(parameters);
	}

	@Override
	public void upload(List<CloudParameter> parameters) {
		appendID(parameters);
		parameters.add(new CloudParameter(CloudVariable.PROCESS, CloudVariable.ADD));
		String request = buildRequestString(parameters);
		String result = request(request);
		if (!result.isEmpty()) {
			System.err.println("SERVER REPORTED AN ERROR: " + result);
		}
	}

	@Override
	public List<Map<String, String>> retrieve(List<CloudParameter> parameters, List<String> values) {
		appendID(parameters);
		parameters.add(new CloudParameter(CloudVariable.PROCESS, CloudVariable.RETRIEVE));
		String result = request(getRequestString(parameters));
		// System.out.println(parameters);
		// System.out.println(getRequestString(parameters));
		// System.out.println("RESULT");
		// System.out.println(result);
		return parseResult(result, values);
	}

	private void appendID(List<CloudParameter> parameters) {
		parameters.add(new CloudParameter("id", ((Integer) myID).toString()));
	}

	private String request(String request) {
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(request);
		String result = "";
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			InputStream responseBody = method.getResponseBodyAsStream();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			StringWriter writer = new StringWriter();
			IOUtils.copy(responseBody, writer, "UTF-8");
			result = writer.toString();

		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}

	private String buildRequestString(List<CloudParameter> parameters) {
		parameters.add(new CloudParameter("id", myGlobalConfig.getString("GroupID")));
		StringBuilder request = new StringBuilder();
		request.append(myServerAddress).append(myServerTarget).append(myConfig.getString("RequestStart"))
				.append(convertParameters(parameters));
		return request.toString();
	}

	private String convertParameters(List<CloudParameter> parameters) {
		return parameters.stream().filter((p) -> !p.getValue().isEmpty())
				.map((p) -> p.getRequest(this::convertParameter))
				.reduce((s1, s2) -> s1 + myConfig.getString("RequestDelimeter") + s2).orElseGet(() -> "");
	}

	private String convertParameter(List<String> input) {
		input = input.stream().map((s) -> URLEncoder.encode(s)).collect(Collectors.toList());

		return String.format(myConfig.getString("ConversionString"), input.get(0), input.get(1));
	}

}
