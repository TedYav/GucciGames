package voogasalad_GucciGames.gameAuthoring.gui.map.cell;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.model.DisplayMapObject;

public class MapObjectContainer extends GridPane {

	private final Map<DisplayMapObject, ImageView> myObjects = new HashMap<>();
	private final IGuiGaeController myController;
	private final IntegerProperty mySize = new SimpleIntegerProperty(1);

	public MapObjectContainer(IGuiGaeController controller) {
		myController = controller;
		mySize.addListener((ob, oV, nV) -> redraw(nV.intValue()));
	}

	public void add(DisplayMapObject object) {
		ImageView view = myController.getMapObjectImage(object);
		myObjects.put(object, view);
		fit(view);
		int idx = myObjects.size() - 1;
		add(view, idx % mySize.intValue(), idx / mySize.intValue());
		mySize.set((int) Math.ceil(Math.sqrt(myObjects.size())));
	}

	public boolean remove(DisplayMapObject object) {
		ImageView view = myObjects.remove(object);
		if (view != null) {
			view.fitWidthProperty().unbind();
			view.fitHeightProperty().unbind();
			getChildren().remove(view);
			int newSize = (int) Math.ceil(Math.sqrt(myObjects.size()));
			if (mySize.intValue() == newSize)
				redraw(newSize);
			mySize.set(newSize);
			return true;
		}
		return false;
	}

	public void clear() {
		myObjects.forEach((k, v) -> {
			v.fitWidthProperty().unbind();
			v.fitHeightProperty().unbind();
		});
		myObjects.clear();
		getChildren().clear();
		mySize.set(1);
	}

	public void setOwner(int id) {
		myObjects.keySet().forEach(k -> k.setOwnerID(id));
	}

	private void redraw(int size) {
		getChildren().clear();
		int idx = 0;
		for (DisplayMapObject k : myObjects.keySet()) {
			add(myObjects.get(k), idx % size, idx / size);
			idx++;
		}
	}

	private void fit(ImageView view) {
		view.fitWidthProperty().bind(widthProperty().divide(mySize));
		view.fitHeightProperty().bind(heightProperty().divide(mySize));
	}

	public Set<DisplayMapObject> getObjects() {
		return Collections.unmodifiableSet(myObjects.keySet());
	}

}
