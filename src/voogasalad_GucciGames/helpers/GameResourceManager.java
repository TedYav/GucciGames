package voogasalad_GucciGames.helpers;

import java.util.List;

import javafx.scene.image.Image;

public interface GameResourceManager {

	public List<String> getImages();
	
	public List<String> getImages(String directory);
	
	public Image getImage(String URI);
	
	public void toggleCopyOnAccess(boolean copy);
	
}
