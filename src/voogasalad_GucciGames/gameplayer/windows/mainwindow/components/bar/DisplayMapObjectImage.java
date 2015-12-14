package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class DisplayMapObjectImage extends DisplayComponent implements ListChangeListener<PlayerMapObjectInterface> {
	private List<PlayerMapObjectInterface> mapObjectsOnCell;
	private Pane display;
	private Image buffer;
	private ImageView imgView;
	private ResourceBundle myBundle = PlayerConfig.load("components.Bar");
	private ResourceBundle myCssBundle = PlayerConfig.load(myBundle.getString("cssclass"));

	public DisplayMapObjectImage(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		display = new FlowPane();
		display.getStyleClass().add(myCssBundle.getString("leftimageflowpane"));
		mapObjectsOnCell = new ArrayList<PlayerMapObjectInterface>();
		updateImages();
	}

	public void updateImages() {
		display.getChildren().clear();
		mapObjectsOnCell.forEach(m->initializeImage(m));
		if (display.getChildren().size() == 0) {
			showImagePlaceholder(myBundle.getString("imageplaceholder"));
		}
	}

	private void initializeImage(PlayerMapObjectInterface m) {
		if (m != null) {
			buffer = getController().getResource().getImage(m.getImageURI());
		}
		imgView=createImageView(buffer);
		imgView.setOnMouseClicked(e -> {
			updateActiveMapObject(m);
		});
		display.getChildren().add(imgView);
	}
	
	private ImageView createImageView(Image buffer) {
	    ImageView imageView = new ImageView(buffer);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(Integer.parseInt(myBundle.getString("imagefitwidth")));
            return imageView;
	}

	private void updateActiveMapObject(PlayerMapObjectInterface mapObj) {
		getController().setActiveMapObject(mapObj);
	}

	@Override
	public Parent getParent() {
		return display;
	}

	/**
	 * Dev method
	 * 
	 * @param url
	 */
	private void showImagePlaceholder(String url) {
		buffer = getController().getResource().getImage(url);
		imgView = createImageView(buffer);
		imgView.setOnMouseClicked(e -> {
			updateActiveMapObject(null);
		});
		display.getChildren().add(imgView);
	}

	@Override
	public ListChangeListener<PlayerMapObjectInterface> getListener() {
		return this;
	}

	@Override
	public void updateDisplay() {
		return;
	}

	@Override
	public void onChanged(Change c) {
		while (c.next()) {
			List<PlayerMapObjectInterface> list = c.getList();
			if (list.size() > 0) {
				mapObjectsOnCell.clear();
				list.forEach(o->mapObjectsOnCell.add(o));
				updateImages();
				setDefaultSelectedImage();
			}
		}
	}
	
	private void setDefaultSelectedImage() {
	    if (mapObjectsOnCell.size() > 0) {
                updateActiveMapObject(
                                mapObjectsOnCell.stream().reduce((u1, u2) -> u2).orElseGet(() -> mapObjectsOnCell.get(0)));
            }
	}
}
