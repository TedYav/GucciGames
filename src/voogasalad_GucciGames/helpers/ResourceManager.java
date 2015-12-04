package voogasalad_GucciGames.helpers;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class ResourceManager {

	private ImageDatabase myImageDatabase;
	private ImageAverager myImageAverager;
	
	public ResourceManager(){
		myImageDatabase = new ImageDatabase();
		myImageAverager = new ImageAverager(this);
	}
	
	public Image getImage(String URI){
		return myImageDatabase.request(URI);
	}
	
	public Color getImageColor(String URI){
		return myImageAverager.request(URI);
	}
	
}
