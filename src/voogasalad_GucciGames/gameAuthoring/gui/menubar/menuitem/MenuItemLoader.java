package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

public class MenuItemLoader {

	private static final String PREFIX = MenuItemLoader.class.getPackage().getName();
	private static final String PATH = PREFIX.replace(".", "/") + "/menu.properties";

	public List<Menu> load(IGuiGaeController controller) {
		List<String> nameList = new ArrayList<>();
		Map<String,Menu> map = new HashMap<>();
		Properties prop = getProp();
		System.out.println(prop);
		prop.forEach((key, val) -> {
			String name = key.toString();
			String[] attr = val.toString().split(",");
			if(!map.containsKey(attr[1])){
				nameList.add(attr[1]);
				map.put(attr[1], new Menu(attr[1]));
			}
			map.get(attr[1]).getItems().add(getItem(name,attr[0],controller));
		});
		List<Menu> list = new ArrayList<>();
		nameList.forEach(e->list.add(map.get(e)));
		return list;
	}

	private Properties getProp() {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	private MenuItem getItem(String className, String itemName, IGuiGaeController controller) {
		@SuppressWarnings("rawtypes")
		Class[] types = { String.class, IGuiGaeController.class };
		try {
			return (MenuItem) Class.forName(PREFIX + "." + className).getDeclaredConstructor(types)
					.newInstance(itemName, controller);
		} catch (Exception e) {
			System.err.println(className + " not found");
			return null;
		}
	}
	
	public static void main(String[] args){
		MenuItemLoader loader = new MenuItemLoader();
		loader.load(null);
	}
}
