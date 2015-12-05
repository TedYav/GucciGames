package voogasalad_GucciGames.helpers;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ImageDatabase implements ResourceDatabase<Image> {

	private Map<String, Image> myImageMap;
	
	public ImageDatabase(){
		myImageMap = new HashMap<>();
	}
	
	@Override
	public Image request(String URI) {
		if(!myImageMap.containsKey(URI)){
			System.out.println(URI);
			myImageMap.put(URI, new Image(URI));
		}
		return myImageMap.get(URI);
	}
	
}
