package voogasalad_GucciGames.gameplayer.controller.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;


/**
 * TESTING CLASS FOR UNITS
 * @author Ted Yavuzkurt
 *
 */
public class DummyUnit extends ADummy implements PlayerMapObjectInterface {
	
	private int myX, myY;
	
	public DummyUnit(int x, int y){
		myX = x;
		myY = y;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Mario";
	}

	@Override
	public String getImageURI() {
		return "player/images/mario.png";
	}

	@Override
	public List<String> getActionNames() {
		// TODO Auto-generated method stub
		return Arrays.asList("Attack", "Destroy", "Pwn", "Wingame");
	}

	public List<ATargetCoordinate> getActionTargets(String action) {
		List<ATargetCoordinate> myTargets = new ArrayList<>();
		for(int i=-2; i<=2; i++){
			for(int j=-2; j<=2; j++){
				if(i==0 & j==0)
					continue;
				myTargets.add(new TargetCoordinateSingle(myX+i, myY+j));
			}
		}
		return myTargets;
	}

	public List<PlayerMapObjectInterface> performAction(String action, ATargetCoordinate coordinate) {
		myX = ((Double)coordinate.getListOfCoordinates().get(0).getCenterX()).intValue();
		myY = ((Double)coordinate.getListOfCoordinates().get(0).getCenterY()).intValue();
		ArrayList<PlayerMapObjectInterface> target = new ArrayList<>();
		target.add(this);
		return target;
	}

	@Override
	public int getLayer() {
		
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Map<String, String> getAttributes() {
		Map<String, String> attribs = new HashMap<>();
		attribs.put("HP", "100");
		attribs.put("Damage", "7-9");
		return attribs;
	}

	@Override
	public int getPlayerID() {
		// TODO Auto-generated method stub
		return (myX % 2);
	}

	@Override
	public ATargetCoordinate getCoordinate() {
		return new TargetCoordinateSingle(myX, myY);
	}

}