package voogasalad_GucciGames.helpers;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import voogasalad_GucciGames.gameData.GameDataManager;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GameInfoToGameData;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class ResourceManager implements GameResourceManagerToGAE, GameResourceManagerToPlayer{

	private ImageDatabase myImageDatabase;
	private ImageAverager myImageAverager;
	
	private static final String IMAGE = "Image";
	private static final String EXTENSION = "Extension";
	
	private GameInfoToGameData myGame;
	private String myPath;
	
	private GameDataManager myData;
	
	private boolean copyOnAccess = false;
	
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.helpers.config.ResourceManager");
	
	public ResourceManager(){
		this(null);
	}
	
	public static void main(String[] args){
		ResourceManager r = new ResourceManager();
		System.out.println(r.getExtensions(IMAGE));
		
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
		return myData.getResources(getExtensions(IMAGE));
	}
	
	private List<String> getExtensions(String type) {
		return myConfig.keySet().stream()
				.filter( s -> s.startsWith(type + EXTENSION))
				.map( s -> myConfig.getString(s))
				.collect(Collectors.toList());
	}

	public Image getImage(String URI){
		copyImageIfToggled(URI);
		return myImageDatabase.request(myPath + URI);
	}
	
	private void copyImageIfToggled(String URI) {
		if(copyOnAccess){
			myData.copyResourceToGame(URI, myGame);
		}
	}

	public Color getImageColor(String URI){
		copyImageIfToggled(URI);
		return myImageAverager.request(myPath + URI);
	}

	@Override
	public List<String> getImages(String directory) {
		return myData.getResources(getExtensions(IMAGE), myPath + directory);
	}

	@Override
	public void toggleCopyOnAccess(boolean copy) {
		copyOnAccess = copy;
	}
	
}
