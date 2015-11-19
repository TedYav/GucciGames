package voogasalad_GucciGames.datastructures;

import javafx.scene.image.Image;

public interface ResourceDatabase<T> {

	public T request(String URI);
	
}
