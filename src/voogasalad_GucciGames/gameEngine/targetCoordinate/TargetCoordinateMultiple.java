package voogasalad_GucciGames.gameEngine.targetCoordinate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TargetCoordinateMultiple extends ATargetCoordinate {

	private List<TargetCoordinateSingle> myCoordinates;

	public TargetCoordinateMultiple(){
		this.myCoordinates = new ArrayList<>();
	}

	public TargetCoordinateMultiple(List<ATargetCoordinate> result) {
		this();

		for(ATargetCoordinate coord : result){
			addCoordinate(coord);
		}
	}


	public void addTargetCoodinateSingle(ATargetCoordinate coord){
		myCoordinates.add((TargetCoordinateSingle) coord);
	}

	public void addTargetCoodinateMultiple(ATargetCoordinate coord){
		TargetCoordinateMultiple multCoord = (TargetCoordinateMultiple) coord; 
		myCoordinates.addAll(multCoord.myCoordinates);
	}

	// NOT ACTUALLY ADDING TO LIST! FIX THIS!
	public void addCoordinate(ATargetCoordinate coord){

		Method m;
		try {
			m = Class.forName(this.getClass().getName()).getMethod("add" + coord.getClass().getSimpleName(),ATargetCoordinate.class);
			m.invoke(coord);

		} catch (Exception e){
			e.printStackTrace();
		}


	}

	//public List<ATargetCoordinate> getCoordinates(){
	//	return this.myCoordinates;
	//}

	@Override
	public ATargetCoordinate clone() {
		// TODO Auto-generated method stub
		List<ATargetCoordinate> myList = new ArrayList<ATargetCoordinate>();

		for(ATargetCoordinate coord : this.myCoordinates){
			myList.add((ATargetCoordinate) coord);
		}

		return new TargetCoordinateMultiple(myList);
	}

	@Override
	public List<TargetCoordinateSingle> getListOfCoordinates() {
		// TODO Auto-generated method stub
		return myCoordinates;
	}

}
