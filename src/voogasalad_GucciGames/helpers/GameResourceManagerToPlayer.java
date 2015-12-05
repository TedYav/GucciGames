package voogasalad_GucciGames.helpers;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;

public interface GameResourceManagerToPlayer {
	
	public Image getImage(String URI);
	
	public Color getImageColor(String URI);

	public void setGame(GameInfo game);
	
}
