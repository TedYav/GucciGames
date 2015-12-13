package voogasalad_GucciGames.gameEngine.targetCoordinate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TargetCoordinateMultiple extends ATargetCoordinate {

	private List<TargetCoordinateSingle> myCoordinates;

	public TargetCoordinateMultiple() {
		this.myCoordinates = new ArrayList<>();
	}

	public TargetCoordinateMultiple(List<ATargetCoordinate> result) {
		this();

		for (ATargetCoordinate coord : result) {
			addCoordinate(coord);
		}
	}

	public void addTargetCoordinateSingle(ATargetCoordinate coord) {
		if (!this.myCoordinates.contains(coord)) {
			myCoordinates.add((TargetCoordinateSingle) coord);
		}
	}

	public void addTargetCoordinateMultiple(ATargetCoordinate coord) {
		TargetCoordinateMultiple multCoord = (TargetCoordinateMultiple) coord;
		multCoord.getListOfCoordinates().stream().forEach(coor -> this.addTargetCoordinateSingle(coor));
	}

	// NOT ACTUALLY ADDING TO LIST! FIX THIS!
	public void addCoordinate(ATargetCoordinate coord) {

		Method m;
		try {
			m = Class.forName(this.getClass().getName()).getMethod("add" + coord.getClass().getSimpleName(),
					ATargetCoordinate.class);
			m.invoke(((ATargetCoordinate) coord));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// public List<ATargetCoordinate> getCoordinates(){
	// return this.myCoordinates;
	// }

	@Override
	public ATargetCoordinate clone() {
		// TODO Auto-generated method stub
		List<ATargetCoordinate> myList = new ArrayList<ATargetCoordinate>();

		for (ATargetCoordinate coord : this.myCoordinates) {
			myList.add((ATargetCoordinate) coord);
		}

		return new TargetCoordinateMultiple(myList);
	}

	@Override
	public List<TargetCoordinateSingle> getListOfCoordinates() {
		// TODO Auto-generated method stub
		return myCoordinates;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		TargetCoordinateMultiple other = (TargetCoordinateMultiple) o;
		if (this.myCoordinates.size() != other.myCoordinates.size()) {
			return false;
		}
		for (int i = 0; i < this.myCoordinates.size(); i++) {
			if (!this.myCoordinates.get(i).equals(other.myCoordinates.get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int num = 0;
		for (TargetCoordinateSingle coor : this.myCoordinates) {
			num += coor.hashCode();
		}
		return num;
	}

}
