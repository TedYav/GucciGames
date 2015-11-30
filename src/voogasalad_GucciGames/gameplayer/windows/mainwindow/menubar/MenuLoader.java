package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class MenuLoader {
	private static final String PREFIX = MenuLoader.class.getPackage().getName();
	private static final String MENU_PATH = PREFIX + ".MenuItems";
	private static final String LIST_PATH = PREFIX + ".MenuLists";
	private static final String NAMES_PATH = PREFIX + ".ItemNames";


	
	public List<Menu> load(GameControllerInterface controller){		
		List<String> nameList = new ArrayList<>();
		Map<String, Menu> map = new HashMap<>();
		ResourceBundle listBundle = ResourceBundle.getBundle(LIST_PATH);
		ResourceBundle menuBundle = ResourceBundle.getBundle(MENU_PATH);
		ResourceBundle namesBundle = ResourceBundle.getBundle(NAMES_PATH);
		
		listBundle.keySet().stream().forEach(e -> {
			nameList.add(e);
			map.put(e, new Menu(e));
		});
		
		listBundle.keySet().stream().forEach(dropdownname -> {
			Arrays.asList(listBundle.getString(dropdownname).split(",")).stream()
			.forEach(componentName -> {
				try {
					map.get(dropdownname).getItems()
					.add(getItem(menuBundle.getString(componentName), 
							namesBundle.getString(componentName), controller));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			});
		});
		List<Menu> list = new ArrayList<>();
		nameList.forEach(e -> list.add(map.get(e)));
		return list;
	}
	
	private MenuItem getItem(String className, String itemName, GameControllerInterface controller) throws Exception {
		return (MenuItem) Class.forName(PREFIX + "." + className)
				.getDeclaredConstructor(new Class[] { String.class, GameControllerInterface.class })
				.newInstance(itemName, controller);
	}


}
