package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class MenuLoader {
	private static final String PREFIX = MenuLoader.class.getPackage().getName();
	private static final String MENU_PATH = PREFIX.replace(".", "/") + "/menubar.properties";
	private static final String LIST_PATH = PREFIX.replace(".", "/") + "/menulist.properties";

	
	public List<Menu> load(GameControllerInterface controller){		
		List<String> nameList = new ArrayList<>();
		Map<String, Menu> map = new HashMap<>();
		ResourceBundle menuBundle = ResourceBundle.getBundle(MENU_PATH);
		ResourceBundle listBundle = ResourceBundle.getBundle(LIST_PATH);
		
		listBundle.keySet().stream().forEach(e -> {
			nameList.add(e);
			map.put(e, new Menu(e));
		});
		
	
		
		
		
		
		map.get(attr[1]).getItems().add(getItem(name, attr[0], controller));
		
		
		menuBundle.keySet().stream().forEach(k -> {
			Arrays.asList(listBundle.getString(e).split(",")).stream()
			.forEach(e -> {
				
			});
			
			
			map.get(k)
			
			
		});
		

	}
	
	private MenuItem getItem(String className, String itemName, AGuiGaeController controller) throws Exception {
		return (MenuItem) Class.forName(PREFIX + "." + className)
				.getDeclaredConstructor(new Class[] { String.class, AGuiGaeController.class })
				.newInstance(itemName, controller);
	}


}
