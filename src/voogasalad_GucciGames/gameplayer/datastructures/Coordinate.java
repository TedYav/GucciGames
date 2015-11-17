package voogasalad_GucciGames.gameplayer.datastructures;

import javafx.geometry.Point2D;
import voogasalad_GucciGames.gameplayer.controller.dummy.TargetCoordinate;

public class Coordinate {

	public static TargetCoordinate PointToCoordinate(Point2D coord){
		return new TargetCoordinate(((Double)coord.getX()).intValue(), ((Double)coord.getY()).intValue());
	}
	
	public static Point2D CoordinateToPoint(TargetCoordinate coord){
		return new Point2D(coord.getX(), coord.getY());
	}
}
