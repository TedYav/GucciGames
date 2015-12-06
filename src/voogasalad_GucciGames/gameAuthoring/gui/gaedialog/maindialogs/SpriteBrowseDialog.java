package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.List;

import javafx.scene.image.ImageView;
import voogasalad.util.fxsprite.Sprite;
import voogasalad_GucciGames.helpers.GameResourceManagerToGAE;

public class SpriteBrowseDialog extends ImageBrowseDialog {

	public SpriteBrowseDialog(GameResourceManagerToGAE resManager) {
		super(resManager, null);
	}
	@Override
	protected List<String> getImgs(String type) {
		return myResManager.getSprites();
	}
	
	@Override
	protected ImageView getImgFromDatabase(String URI) {
		Sprite s = myResManager.getSprite(URI);
		s.play();
		return s;
	}

}
