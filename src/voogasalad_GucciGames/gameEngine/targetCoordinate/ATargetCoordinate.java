package voogasalad_GucciGames.gameEngine.targetCoordinate;

public abstract class ATargetCoordinate {

	public abstract ATargetCoordinate clone();

	public double getCenterX() {
		return ((TargetCoordinateSingle)this).getCenterX();
	}
	
	public double getCenterY() {
		return ((TargetCoordinateSingle)this).getCenterY();
	}
	
}
