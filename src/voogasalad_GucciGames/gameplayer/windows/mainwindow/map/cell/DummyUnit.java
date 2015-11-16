package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

import java.util.Arrays;
import java.util.List;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.MapObjectBasicType;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.TargetCoordinate;


/**
 * TESTING CLASS FOR UNITS
 * @author Ted Yavuzkurt
 *
 */
public class DummyUnit implements PlayerMapObjectInterface {
	
	private int myX, myY;
	
	public DummyUnit(int x, int y){
		myX = x;
		myY = y;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "DUMMY";
	}

	@Override
	public String getImageURI() {
		return "player/images/dummytexture.jpg";
	}

	@Override
	public List<String> getActionNames() {
		// TODO Auto-generated method stub
		return Arrays.asList("Build", "Destroy", "Pwn", "Wingame");
	}

	@Override
	public MapObjectBasicType getBasicType() {
		return MapObjectBasicType.GROUND;
	}

	@Override
	public List<TargetCoordinate> getActionTarget() {
		return null;
	}

	@Override
	public void performAction(String action, TargetCoordinate coordinate) {
		// TODO Auto-generated method stub
		
	}

}
