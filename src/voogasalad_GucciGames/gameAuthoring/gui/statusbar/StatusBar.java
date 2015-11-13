package voogasalad_GucciGames.gameAuthoring.gui.statusbar;

import javafx.scene.layout.HBox;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

public class StatusBar extends HBox{
	
	IGuiGaeController myController;
	
	StatusBar(IGuiGaeController controller){
		myController = controller;
		setPrefHeight(30);
	}
}
