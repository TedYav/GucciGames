package voogasalad_GucciGames.testing;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.CharacteristicFactory;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class TestCharacteristics {

	@Test
	public void test() {
		CharacteristicFactory cf = new CharacteristicFactory();
		Map<String, AMapObjectCharacteristic> answer = new TreeMap<>();
		answer.put("AttackCharacteristic", new AttackCharacteristic(1,1,1));
		answer.put("MovableCharacteristic", new MovableCharacteristic(1, 1));
		System.out.println(cf.tempCharacteristic());
		assertEquals(answer, cf.tempCharacteristic());
		fail("Not yet implemented");
	}

}
