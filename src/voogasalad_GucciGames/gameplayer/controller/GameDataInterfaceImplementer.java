package voogasalad_GucciGames.gameplayer.controller;

import javafx.scene.control.Button;
import javafx.stage.Popup;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.SomeData;

public class GameDataInterfaceImplementer implements GameDataInterface{

	@Override
	public void loadGames() {
		// TODO Auto-generated method stub
		Popup p = new Popup();
		
		MainGameEngine m = new MainGameEngine(new SomeData());
		Button b = new Button();
		b.setOnMouseClicked(e->{m.initialize(new SomeData());});
		p.getContent().add(b);
	}

}
