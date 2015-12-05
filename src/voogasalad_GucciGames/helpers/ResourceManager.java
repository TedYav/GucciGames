package voogasalad_GucciGames.helpers;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import voogasalad_GucciGames.gameData.GameDataManager;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class ResourceManager implements GameResourceManager{

	private ImageDatabase myImageDatabase;
	private ImageAverager myImageAverager;
	
	private GameInfo myGame;
	private String myPath;
	
	private GameDataManager myData;
	
	public ResourceManager(){
		this(null);
	}
	
	public ResourceManager(GameInfo game){
		myImageDatabase = new ImageDatabase();
		myImageAverager = new ImageAverager(this);
		myGame = game;
		setPath();
	}
	
	private void setPath() {
		myPath = (myGame == null) ? "" : myData.getGamePath(myGame);
	}

	public void setGame(GameInfo game){
		myGame = game;
		setPath();
	}
	
	public List<String> getImages(){
		return null;
	}
	
	public Image getImage(String URI){
		return myImageDatabase.request(URI);
	}
	
	public Color getImageColor(String URI){
		return myImageAverager.request(URI);
	}

	@Override
	public List<String> getImages(String directory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void toggleCopyOnAccess(boolean copy) {
		// TODO Auto-generated method stub
		
	}
	
}
