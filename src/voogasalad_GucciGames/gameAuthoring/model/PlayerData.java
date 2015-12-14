// This entire file is part of my masterpiece.
// Daniel McKee

package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ParamObjParser;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.model.factories.PlayerCharacteristicFactory;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

public class PlayerData {
	private Map<Integer, GamePlayerPerson> myMapOfPlayers = new HashMap<>();
	private Map<String, ObjectParam> myPlayerCharacteristicParams = new HashMap<>();
	private PlayerCharacteristicFactory myPlayerCharacteristicFactory;

	public PlayerData() {
		myPlayerCharacteristicFactory = new PlayerCharacteristicFactory();
		ParamObjParser parser = new ParamObjParser();
		Set<ObjectParam> playerChar = parser.getPlayerChars();
		for (ObjectParam characteristics : playerChar) {
			myPlayerCharacteristicParams.put(characteristics.getName(), characteristics);
		}
	}

	public List<ObjectParam> getAllPlayerCharParams() {
		return new ArrayList<>(myPlayerCharacteristicParams.values());
	}

	public List<ObjectParam> getSelectedPlayerCharParams(List<String> selectedChar) {
		return myPlayerCharacteristicParams.values().stream().filter(c -> selectedChar.contains(c.getName()))
				.collect(Collectors.toList());
	}

	public void setNumberOfPlayers(int n) {
		for (int i = 0; i < n; i++) {
			myMapOfPlayers.put(i, new GamePlayerPerson(i));
		}
	}

	public int getNumberOfPlayers() {
		return myMapOfPlayers.size();
	}

	public void clearObjects() {
		myMapOfPlayers.values().forEach(p -> {
			p.clearMapObjects();
		});
	}

	public void addPlayerCharacteristic(int playerID, ObjParamValue param) {
		myMapOfPlayers.get(playerID).addCharacterstic(param.getName(),
				myPlayerCharacteristicFactory.create(myPlayerCharacteristicParams, param));

	}

	public Map<Integer, GamePlayerPerson> getMapOfPlayers() {
		return myMapOfPlayers;
	}

}
