package voogasalad_GucciGames.testing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import junit.framework.TestCase;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;

public class TestCharacteristicFactory extends TestCase{

	public void testCharacteristicFactory(){
		
		Map<String, ObjParam> map = new HashMap<>();
		ObjParam op = new ObjParam("SuperHealth",ObjType.MAP_CHAR,0);
		map.put(op.getName(), op);
	}
	
}
