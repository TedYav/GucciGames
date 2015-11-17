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
public class DummyTile implements PlayerMapObjectInterface {
	
	private int myX, myY;
	
	public DummyTile(int x, int y){
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
		return ((myX+myY)%2 == 0)?"player/images/dummytexture.jpg":"player/images/dummytexture2.jpg";
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
	public List<TargetCoordinate> getActionTargets(String action) {
		return Arrays.asList(new TargetCoordinate(myX, myY));
	}

	@Override
	public List<PlayerMapObjectInterface> performAction(String action, TargetCoordinate coordinate) {
		return new ArrayList<>();
	}

	@Override
	public int getLayer() {
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, String> getAttributes() {
		return new HashMap<>();
	}

	@Override
	public int getPlayerID() {
		// TODO Auto-generated method stub
		return 0;
	}

    @Override
    public ATargetCoordinate getCoordinate () {
        return new TargetCoordinateSingle(myX,myY);
    }

}
