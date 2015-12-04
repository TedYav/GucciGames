package voogasalad_GucciGames.helpers;

import java.util.List;

import javafx.scene.paint.Color;

public class ColorUtility {

	public static Color average(List<Color> colors){
		double red = 0;
		double green = 0;
		double blue = 0;
		for(Color c : colors){
			red += (c.getRed()*c.getOpacity());
			green += (c.getGreen()*c.getOpacity());
			blue += (c.getBlue()*c.getOpacity());
		}
		red /= (double)colors.size();
		green /= (double)colors.size();
		blue /= (double)colors.size();
		return new Color(red, green, blue, 1.0);
	}
	
}
