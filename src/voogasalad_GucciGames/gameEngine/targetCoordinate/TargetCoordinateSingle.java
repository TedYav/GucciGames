package voogasalad_GucciGames.gameEngine.targetCoordinate;

public class TargetCoordinateSingle extends ATargetCoordinate{
	private double myCenterX, myCenterY;
	
	public TargetCoordinateSingle(double x, double y){
		this.myCenterX = x;
		this.myCenterY = y;
	}

	@Override
	public ATargetCoordinate clone() {
		// TODO Auto-generated method stub
		return new TargetCoordinateSingle(myCenterX, myCenterY);
	}
	
	@Override
	public boolean equals(Object o){
		TargetCoordinateSingle other = (TargetCoordinateSingle) o;
		return (this.myCenterX == other.myCenterX) && (this.myCenterY == other.myCenterY); 
	}

	public double getCenterX() {
		return myCenterX;
	}

	public double getCenterY() {
		return myCenterY;
	}
	
}
