package voogasalad_GucciGames.testing;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;

public class TestPlayer {

	/*
	 * 
	 * myMap.setStyle("-fx-background-color: red");
	 * myMap.setMinWidth(myCellsWide * myCellSize);
	 * myMap.setMinHeight(myCellsTall * myCellSize); for(int i=0; i<myCellsWide;
	 * i++){ for(int j=0; j<myCellsTall; j++){ Rectangle r = new Rectangle();
	 * r.setWidth(myCellSize); r.setHeight(myCellSize);
	 * r.setFill(((i+j)%2==0)?Color.WHEAT:Color.RED); myMap.add(r, i, j); } }
	 */

	public static List<PlayerMapObjectInterface> getDummyMap(int size) {
		ArrayList<PlayerMapObjectInterface> dummyMap = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// dummyMap.add(new DummyTile(i,j));
				if ((i + j) % 15 == 0) {
					// dummyMap.add(new DummyUnit(i,j));
				}
			}
		}
		return dummyMap;
	}

}
