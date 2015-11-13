package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

public class MenuItemLoader {

	private static final String PREFIX = MenuItemLoader.class.getPackage().getName();
	private static final String PATH = PREFIX.replace(".", "/") + "/menu.properties";

	public List<Menu> load(IGuiGaeController controller) throws Exception{
		List<String> nameList = new ArrayList<>();
		Map<String, Menu> map = new HashMap<>();
		Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(PATH));
		while (scanner.hasNextLine()) {
			String s = scanner.nextLine().replaceAll("#.*+", "").trim();
			if (s.length() == 0 || !s.contains("="))
				continue;
			String[] t = s.split("=");
			String name = t[0].trim();
			String[] attr = t[1].trim().split(",");
			if (!map.containsKey(attr[1])) {
				nameList.add(attr[1]);
				map.put(attr[1], new Menu(attr[1]));
			}
			map.get(attr[1]).getItems().add(getItem(name, attr[0], controller));
		}
		scanner.close();
		List<Menu> list = new ArrayList<>();
		nameList.forEach(e -> list.add(map.get(e)));
		return list;
	}

	private MenuItem getItem(String className, String itemName, IGuiGaeController controller) throws Exception {
		return (MenuItem) Class.forName(PREFIX + "." + className)
				.getDeclaredConstructor(new Class[] { String.class, IGuiGaeController.class })
				.newInstance(itemName, controller);
	}

	public static void main(String[] args) throws Exception{
		MenuItemLoader loader = new MenuItemLoader();
		loader.load(null);
	}
}
