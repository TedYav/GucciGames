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

public class PlayerData implements IPlayerData{
	private Map<Integer, GamePlayerPerson> myMapOfPlayers = new HashMap<>();
	private Map<String, ObjectParam> myPlayerCharacteristicParams = new HashMap<>();
	private PlayerCharacteristicFactory myPlayerCharacteristicFactory;
	private int nextID = -1;

	
	public PlayerData() {
		myPlayerCharacteristicFactory = new PlayerCharacteristicFactory();
		ParamObjParser parser = new ParamObjParser();
		Set<ObjectParam> playerChar = parser.getPlayerChars();
		for (ObjectParam characteristics : playerChar) {
			myPlayerCharacteristicParams.put(characteristics.getName(), characteristics);
		}
	}

	@Override
	public List<ObjectParam> getAllPlayerCharParams() {
		return new ArrayList<>(myPlayerCharacteristicParams.values());
	}

	@Override
	public List<ObjectParam> getSelectedPlayerCharParams(List<String> selectedChar) {
		return myPlayerCharacteristicParams.values().stream().filter(c -> selectedChar.contains(c.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public void clearObjects() {
		myMapOfPlayers.values().forEach(p -> {
			p.clearMapObjects();
		});
	}

	@Override
	public void addPlayerCharacteristic(int playerID, ObjParamValue param) {
		myMapOfPlayers.get(playerID).addCharacterstic(param.getName(),
				myPlayerCharacteristicFactory.create(myPlayerCharacteristicParams, param));
	}

	@Override
	public Map<Integer, GamePlayerPerson> getMapOfPlayers() {
		return myMapOfPlayers;
	}

	@Override
	public int getNumberOfPlayers() {
		return myMapOfPlayers.size();
	}

	@Override
	public int addPlayer() {
		myMapOfPlayers.put(nextID, new GamePlayerPerson(nextID));
		nextID += 1;
		return nextID - 1;
	}

}
