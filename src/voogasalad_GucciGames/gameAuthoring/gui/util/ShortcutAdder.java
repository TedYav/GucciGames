package voogasalad_GucciGames.gameAuthoring.gui.util;

import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.GAEGui;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class ShortcutAdder {
	private final AGuiGaeController myController;
	private final GAEGui myGui;
	private static final int MAX = 40;
	
	public ShortcutAdder(AGuiGaeController controller, GAEGui gui){
		myController = controller;
		myGui = gui;
		myGui.addEventFilter(KeyEvent.KEY_PRESSED, e->keyPressed(e));
	}
	
	private void keyPressed(KeyEvent e){
		if(!e.isControlDown() || !e.isAltDown())
			return;
		if(e.getCode()==KeyCode.DIGIT1){
			addTiles(MAX);
			e.consume();
		}else if(e.getCode()==KeyCode.DIGIT2){
			addUnits(MAX);
			e.consume();
		}else if(e.getCode()==KeyCode.DIGIT3){
			addSprites(MAX);
			e.consume();
		}else if(e.getCode()==KeyCode.DIGIT4){
			addStructs(MAX);
			e.consume();
		}
	}
	
	private void addTiles(int n){
		List<String> list = myController.getResourceManager().getImages("tiles");
		for(int i=0;i<list.size() && i<n;i++){
			MapObjectType type = new MapObjectType(list.get(i), list.get(i), 0);
			myController.getResourceManager().copyImageToGame(list.get(i));
			myController.createCustomType(type, "tile");
		}
	}
	
	private void addUnits(int n){
		List<String> list = myController.getResourceManager().getImages("units");
		for(int i=0;i<list.size() && i<n;i++){
			MapObjectType type = new MapObjectType(list.get(i), list.get(i), 0);
			myController.getResourceManager().copyImageToGame(list.get(i));
			myController.createCustomType(type, "unit");
		}
	}
	
	private void addSprites(int n){
		List<String> list = myController.getResourceManager().getSprites();
		for(int i=0;i<list.size() && i<n;i++){
			MapObjectType type = new MapObjectType(list.get(i), list.get(i)+":0", 0);
			myController.getResourceManager().copyImageToGame(list.get(i));
			myController.createCustomType(type, "unit");
		}
	}
	
	private void addStructs(int n){
		List<String> list = myController.getResourceManager().getImages("structures");
		for(int i=0;i<list.size() && i<n;i++){
			MapObjectType type = new MapObjectType(list.get(i), list.get(i), 0);
			myController.getResourceManager().copyImageToGame(list.get(i));
			myController.createCustomType(type, "structure");
		}
	}
}
