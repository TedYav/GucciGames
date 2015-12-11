package voogasalad_GucciGames.helpers;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class ImageAverager implements ResourceDatabase<Color>{

	private Map<String, Color> myImageMap;
	private ResourceManager myManager;
	
	public ImageAverager(ResourceManager resourceManager){
		myManager = resourceManager;
		myImageMap = new HashMap<>();
	}
	
	@Override
	public Color request(String URI) {
		if(!myImageMap.containsKey(URI)){
			myImageMap.put(URI, average(URI));
		}
		return myImageMap.get(URI);
	}

	private Color average(String URI) {
		Image source = myManager.getImage(URI);
		PixelReader reader = source.getPixelReader();
		double red = 0, green = 0, blue = 0;
		double count = 1;
		for(int x = 0; x < source.getWidth(); x++){
			for(int y = 0; y < source.getHeight(); y++){
				Color currentColor = reader.getColor(x, y);
				red = (red*count + currentColor.getRed())/(count+1);
				blue = (blue*count + currentColor.getBlue())/(count+1);
				green = (green*count + currentColor.getGreen())/(count+1);
				count++;
			}
		}
		return new Color(red, green, blue, 1.0);
	}
	
	
	
}
