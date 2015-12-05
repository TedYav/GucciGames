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
	
	private String myGameName;
	private GameInfo myGame;
	
	/**
	 * Set to empty string if no game loaded. If game loaded, looks for resources in current game.
	 */
	private String myRootDirectory;
	
	private GameDataManager myData;
	
	private boolean copyOnAccess = false;
	
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.helpers.config.ResourceManager");
//	
//	public static void main(String[] args){
//		ResourceManager r = new ResourceManager("Duvall Tag");
//	}
	
	public ResourceManager(){
		this("");
	}
	
	/**
	 * Constructs a new resource manager for the named game.
	 * When called with this constructor **files will be automatically copied to the game**
	 * @param gameName
	 */
	public ResourceManager(String gameName){
		myImageDatabase = new ImageDatabase();
		myImageAverager = new ImageAverager(this);
		myData = new GameDataManager();
		myGameName = gameName;
		setRoot();
	}
	
	public ResourceManager(GameInfo game){
		this(game.getGameName());
		myGame = game;
	}
	
	private void setRoot() {
		myRootDirectory = (myGame == null) ? "" : myData.getGamePath(myGameName) + myConfig.getString("LocalResourcePath");
	}

	public void loadGame(GameInfo game){
		myGame = game;
		myGameName = myGame.getGameName();
		setRoot();
	}
	
	public List<String> getImages(){
		return myData.getResources(getExtensions(myConfig.getString("ImageExt")), myConfig.getString("ImagePath"));
	}
	
	private List<String> getExtensions(String type) {
		return myConfig.keySet().stream()
				.filter( s -> s.startsWith(type))
				.map( s -> myConfig.getString(s))
				.collect(Collectors.toList());
	}

	public Image getImage(String URI){
		copyImageIfToggled(URI);
		//System.out.println("URL REQUESTED" + URI);
		Image result = null;
		try{
			result = myImageDatabase.request(myRootDirectory + myConfig.getString("ImagePath") + URI);
		}catch(IllegalArgumentException e){
			try{
				result = myImageDatabase.request(myConfig.getString("DefaultRoot") + myConfig.getString("ImagePath") + URI);
			}
			catch(IllegalArgumentException e2){
				throw e2;
			}
		}
		return result;
	}
	
	private void copyImageIfToggled(String URI) {
		if(copyOnAccess){
			myData.copyResourceToGame(myConfig.getString("ImagePath") + URI, myGameName);
		}
	}

	public Color getImageColor(String URI){
		copyImageIfToggled(URI);
		return myImageAverager.request(URI);
	}

	@Override
	public List<String> getImages(String directory) {
		return myData.getResources(getExtensions(myConfig.getString("ImageExt")), myConfig.getString("ImagePath") + directory);
	}

	@Override
	public void toggleCopyOnAccess(boolean copy) {
		copyOnAccess = copy;
	}

	@Override
	public List<String> listImageDirectories() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
