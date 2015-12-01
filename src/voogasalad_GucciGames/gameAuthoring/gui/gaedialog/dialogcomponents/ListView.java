package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.stylesheets.IListView;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Content for Settings ScrollPane
 * @author yingqi
 *
 */
abstract class ListView extends VBox implements IListView{

	
	public abstract void addListElement(String name);
	
	public abstract void removeListItem(ListItem item);
	
	public abstract List<ListItem> getAllListItems();
	
	protected abstract void redraw();
	


}
