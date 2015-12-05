package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class GuiData {
	private ResourceBundle namesBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GuiComponents");
	private List<String> myRightComponents;	// hold class names
	private List<String> myLeftComponents;
	private List<String> myBottomComponents;
	private Map<String, List<String>> myComponents;

	public GuiData () {
		myRightComponents = new ArrayList<String>();
		myLeftComponents = new ArrayList<String>();
		myBottomComponents = new ArrayList<String>();
		myComponents = new HashMap<String, List<String>>();
		List<String> componentNames = Arrays.asList(namesBundle.getString("Components").split(","));
		for(String name: componentNames){
			myComponents.put(name, new ArrayList<String>());
		}
		
	}

	public List<String> getRightComponents() {
		return myRightComponents;
	}

	public void addRightComponent(String component) {
		myRightComponents.add(component);
	}

	public List<String> getLeftComponents() {
		return myLeftComponents;
	}

	public void addLeftComponent(String component) {
		myLeftComponents.add(component);
	}

	public List<String> getBottomComponents() {
		return myBottomComponents;
	}

	public void addBottomComponent(String component) {
		myBottomComponents.add(component);
	}

	public int numberOfComponents() {
		return myRightComponents.size() + myLeftComponents.size() + myBottomComponents.size();
	}

	public void setLeftComponents(List<String> components) {
		myLeftComponents = components;
		
	}

	public void setRightComponents(List<String> components) {
		myRightComponents = components;
	}

	public void setBottomComponents(List<String> components) {
		myBottomComponents = components;
	}

	public void setComponents(String location, List<String> components) {
		myComponents.put(location, components);
		
	}

	public List<String> getComponents(String location) {
		return myComponents.get(location);
	}
	

	
	

}
