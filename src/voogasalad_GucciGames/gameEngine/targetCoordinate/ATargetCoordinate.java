package voogasalad_GucciGames.gameEngine.targetCoordinate;

import java.util.List;

public abstract class ATargetCoordinate {

	public abstract ATargetCoordinate clone();

	public abstract List<TargetCoordinateSingle> getListOfCoordinates();
	
	@Override
	public boolean equals(Object test){
		if(!(test instanceof ATargetCoordinate)){
			return false;
		}
		ATargetCoordinate comparison = ((ATargetCoordinate)test);
		List<TargetCoordinateSingle> theirCoords = comparison.getListOfCoordinates();
		List<TargetCoordinateSingle> myCoords = getListOfCoordinates();
		if(myCoords.size() != theirCoords.size()){
			return false;
		}
		for(int i=0; i<getListOfCoordinates().size(); i++){
			if((myCoords.get(i).getCenterX() != theirCoords.get(i).getCenterX()) ||
					(myCoords.get(i).getCenterY() != theirCoords.get(i).getCenterY())){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString(){
		return getListOfCoordinates().stream()
				.map((c) -> "(" + c.getCenterX() + ", " + c.getCenterY()+ ")")
				.reduce((s1,s2) -> s1 + "\n" + s2).get();
	}

	
}
