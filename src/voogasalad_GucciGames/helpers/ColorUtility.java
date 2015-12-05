package voogasalad_GucciGames.helpers;

import java.util.List;

import javafx.scene.paint.Color;

public class ColorUtility {

	public static Color average(List<Object> myColors){
		double red = 0;
		double green = 0;
		double blue = 0;
		for(Color c : myColors){
			red += (c.getRed()*c.getOpacity());
			green += (c.getGreen()*c.getOpacity());
			blue += (c.getBlue()*c.getOpacity());
		}
		red /= (double)myColors.size();
		green /= (double)myColors.size();
		blue /= (double)myColors.size();
		return new Color(red, green, blue, 1.0);
	}
	
	public static void print(Color c){
		System.out.println("COLOR R: " + c.getRed() + " G: " + c.getGreen() + " B: " + c.getBlue() + " O: " + c.getOpacity());
	}
	
}
