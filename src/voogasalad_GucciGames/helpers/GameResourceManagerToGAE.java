package voogasalad_GucciGames.helpers;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import voogasalad.util.fxsprite.Sprite;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;

public interface GameResourceManagerToGAE {

	public List<String> getImages();
	
	public List<String> getImages(String directory);
	
	public List<String> listImageDirectories();
	
	public Image getImage(String URI);
	
	
	
	/**
	 * Returns the average color of the request image.
	 * @param URI
	 * @return
	 */
	public Color getImageColor(String URI);
	
	/**
	 * If set to true, copies all resources to the Game Directory on access.
	 * MUST BE SET IN GAE.
	 * @param copy
	 */
	public void toggleCopyOnAccess(boolean copy);
	
	public void changeGameName(String newName);

	/**
	 * Returns a sprite with the requested URI
	 * @param URI
	 * @return
	 */
	public Sprite getSprite(String URI);

	/**
	 * Returns a list of sprites available.
	 * @return
	 */
	public List<String> getSprites();
	
	/**
	 * Loads game into the resource manager
	 * @param gameName
	 */
	public void loadGame(String gameName);

	public void copySpriteToGame(String URI);

	public void copyImageToGame(String URI);

	// NOTE: ted will implement this if needed
	//public void copyImageToGame(String imagePath);
}
