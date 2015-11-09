package voogasalad_GucciGames.gamedata.games;

/**
 * Concrete class for Game Data files. Holds the name of a game file
 * as well as the name of the game which it contains.
 * 
 * Can add other fields as needed.
 * @author Ted Yavuzkurt
 *
 */
public class GameDataFile implements GameFile{

	private String myName;
	private String myPath;
	public int myID;
	
	/**
	 * Returns the name of the game to the GUI so it can be drawn.
	 */
	@Override
	public String getName() {
		return myName;
	}
	
	/**
	 * Used in case there are two games with the same name.
	 * Also used to interface with cloud
	 * @return
	 */
	@Override
	public int getID() {
		return myID;
	}
	
	public String getPath() {
		return myPath;
	}

	
}
