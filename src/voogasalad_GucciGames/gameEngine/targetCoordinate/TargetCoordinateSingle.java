package voogasalad_GucciGames.gameEngine.targetCoordinate;

public class TargetCoordinateSingle extends ATargetCoordinate{
	private double centerX, centerY;
	
	public TargetCoordinateSingle(double x, double y){
		this.centerX = x;
		this.centerY = y;
	}

	@Override
	public ATargetCoordinate clone() {
		// TODO Auto-generated method stub
		return new TargetCoordinateSingle(centerX, centerY);
	}
}
