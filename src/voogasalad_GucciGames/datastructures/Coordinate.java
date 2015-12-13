package voogasalad_GucciGames.datastructures;

import javafx.geometry.Point2D;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class Coordinate {

	public static TargetCoordinateSingle PointToCoordinate(Point2D coord) {
		return new TargetCoordinateSingle(((Double) coord.getX()).intValue(), ((Double) coord.getY()).intValue());
	}

	public static Point2D CoordinateToPoint(TargetCoordinateSingle coord) {
		return new Point2D(coord.getCenterX(), coord.getCenterY());
	}

	public static Point2D CoordinateToPoint(ATargetCoordinate coord) {
		return CoordinateToPoint((TargetCoordinateSingle) coord);
	}
}
