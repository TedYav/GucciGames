package voogasalad_GucciGames.gameData.wrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class GuiData {
	private transient ResourceBundle namesBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GuiComponents");
	private List<String> myRightComponents;	// hold class names
	private List<String> myLeftComponents;
	private List<String> myBottomComponents;
	private Map<String, List<String>> myComponents;

	public GuiData () {
		myRightComponents = Arrays.asList(namesBundle.getString("defaultRight").split(","));
		myLeftComponents = Arrays.asList(namesBundle.getString("defaultLeft").split(","));
		myBottomComponents = Arrays.asList(namesBundle.getString("defaultBottom").split(","));
		myComponents = new HashMap<String, List<String>>();
		List<String> componentNames = Arrays.asList(namesBundle.getString("Components").split(","));
		for(String name: componentNames){
			myComponents.put(name, Arrays.asList(namesBundle.getString("default"+name).split(",")));
		}
		
	}

	public int numberOfComponents() {
		return myRightComponents.size() + myLeftComponents.size() + myBottomComponents.size();
	}

	public void setComponents(String location, List<String> components) {
		myComponents.put(location, components);	
	}

	public List<String> getComponents(String location) {
		return myComponents.get(location);
	}
}
