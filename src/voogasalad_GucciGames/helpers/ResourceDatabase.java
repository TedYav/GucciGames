package voogasalad_GucciGames.helpers;

import javafx.scene.image.Image;

public interface ResourceDatabase<T> {

	public T request(String URI);
	
}
