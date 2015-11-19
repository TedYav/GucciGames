package voogasalad_GucciGames.gameEngine.targetCoordinate;

import java.util.List;

public abstract class ATargetCoordinate {

	public abstract ATargetCoordinate clone();

	public abstract List<TargetCoordinateSingle> getListOfCoordinates();
	
	@Override
	public abstract boolean equals(Object o);

	@Override
	public abstract int hashCode();
	
	public String toString(){
		return this.getListOfCoordinates()
		.stream()
		.map((coor) -> "(" + coor.getCenterX() + ", " + coor.getCenterY()+ ")")
		.reduce((coor,str) -> coor + "\n" + str).get();
	}
}
