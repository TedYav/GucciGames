package voogasalad_GucciGames.gameAuthoring.gui.map.cell;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class MapObjectContainer extends GridPane {
	
	private final Map<MapObject,ImageView> myObjects = new HashMap<>();
	private final IGuiGaeController myController;
	private final IntegerProperty mySize = new SimpleIntegerProperty(1);
	
	public MapObjectContainer(IGuiGaeController controller) {
		myController = controller;
		mySize.addListener((ob,oV,nV)->redraw(nV.intValue()));
	}
	

	public boolean add(MapObject object){
		ImageView view = new ImageView(myController.requestImage(object.getImageURI()));
		myObjects.put(object, view);
		fit(view);
		add(view, myObjects.size() % mySize.intValue(), myObjects.size() / mySize.intValue());
		mySize.set((int)Math.ceil(Math.sqrt(myObjects.size())));
		return true;
	}
	
	public boolean remove(MapObject object){
		ImageView view = myObjects.remove(object);
		if(view!=null){
			view.fitWidthProperty().unbind();
			view.fitHeightProperty().unbind();
			getChildren().remove(view);
			int newSize = (int)Math.ceil(Math.sqrt(myObjects.size()));
			if(mySize.intValue()==newSize)
				redraw(newSize);
			mySize.set(newSize);
			return true;
		}
		return false;
	}
	
	public void clear(){
		myObjects.forEach((k,v)->{
			v.fitWidthProperty().unbind();
			v.fitHeightProperty().unbind();
		});
		myObjects.clear();
		getChildren().clear();
		mySize.set(1);
	}
	
	private void redraw(int size) {
		getChildren().clear();
		int idx = 0;
		for(MapObject k:myObjects.keySet()){
			add(myObjects.get(k), idx % size, idx / size);
			idx++;
		}
	}
	
	private void fit(ImageView view){
		view.fitWidthProperty().bind(widthProperty().divide(mySize));
		view.fitHeightProperty().bind(heightProperty().divide(mySize));
	}
	
}
