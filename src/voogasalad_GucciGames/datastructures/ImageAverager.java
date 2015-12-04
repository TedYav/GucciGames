package voogasalad_GucciGames.datastructures;

import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class ImageAverager implements ResourceDatabase<Color>{

	private Map<String, Color> myImageMap;
	private GameControllerInterface myController;
	
	public ImageAverager(GameControllerInterface controller){
		myController = controller;
	}
	
	@Override
	public Color request(String URI) {
		if(!myImageMap.containsKey(URI)){
			myImageMap.put(URI, average(URI));
		}
		return myImageMap.get(URI);
	}

	private Color average(String URI) {
		Image source = myController.requestImage(URI);
		PixelReader reader = source.getPixelReader();
		double red = 0, green = 0, blue = 0;
		double count = 1;
		for(int x = 0; x < source.getWidth(); x++){
			for(int y = 0; y < source.getHeight(); y++){
				Color currentColor = reader.getColor(x, y);
				red = (red + currentColor.getRed())/count;
				blue = (blue + currentColor.getBlue())/count;
				green = (green + currentColor.getGreen())/count;
				count++;
			}
		}
		return new Color(red, blue, green, 1.0);
	}
	
	
	
}
