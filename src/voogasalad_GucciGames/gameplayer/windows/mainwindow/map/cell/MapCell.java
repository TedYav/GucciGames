package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.helpers.ColorUtility;

public abstract class MapCell extends Observable implements MapCellInterface {

	private ResourceBundle myConfig = ResourceBundle
			.getBundle("voogasalad_GucciGames.gameplayer.config.components.MapCell");

	// TODO: factor this into two or three classes eventually
	private StackPane myParent;
	private StackPane myObjectLayer;
	protected Map<Integer, GridPane> myLayerMap;
	private StackPane myOverlayLayer;
	protected Shape myOverlay;

	private boolean selected;
	private boolean active;
	private boolean fogActive;

	private GameControllerInterface myController;

	protected double mySize;
	private Point2D myCoordinate;

	private Map<Integer, List<PlayerMapObjectInterface>> myObjects;

	private FadeTransition myBlinker;

	public MapCell(GameControllerInterface controller, double myCellSize) {
		initializeVariables(controller, myCellSize);
		initializePanes();
		initializeOverlays();
		initializeHandlers();
	}

	// dependent on shape
	protected abstract void initializeOverlayShapes();

	private void initializeVariables(GameControllerInterface controller, double myCellSize) {
		myController = controller;
		mySize = myCellSize;
		myObjects = new HashMap<>();
		myLayerMap = new TreeMap<>();
		selected = false;
		active = false;
		fogActive = false;
	}

	private void initializePanes() {
		myParent = new StackPane();
		myObjectLayer = new StackPane();
		myOverlayLayer = new StackPane();
		myParent.getChildren().addAll(myObjectLayer, myOverlayLayer);
		myParent.getStylesheets().add(myConfig.getString("StyleSheet"));
	}

	private void initializeOverlays() {
		initializeOverlayShapes();
		myOverlayLayer.getChildren().add(myOverlay);
		myBlinker = new FadeTransition(Duration.millis(1000), myOverlay);
		myBlinker.setFromValue(0.0);
		myBlinker.setToValue(0.5);
		myBlinker.setCycleCount(Timeline.INDEFINITE);
		myBlinker.setAutoReverse(true);
	}

	private void initializeHandlers() {
		myParent.setOnMouseClicked(e -> activate());
	}

	public Parent getParent() {
		return myParent;
	}

	public void addObject(PlayerMapObjectInterface object) {
		if (!myObjects.containsKey(object.getLayer())) {
			myObjects.put(object.getLayer(), new ArrayList<PlayerMapObjectInterface>());
		}
		myObjects.get(object.getLayer()).add(object);
		redrawLayer(object.getLayer());
	}

	public void removeObject(PlayerMapObjectInterface object) {
		myObjects.get(object.getLayer()).remove(object);
		myLayerMap.get(object.getLayer()).getChildren().remove(object);
		redrawLayer(object.getLayer());
	}

	@Override
	public void activate() {
		if (myController.actionInProgress()) {
			handleActionInProgress();
		} else {
			myController.cancelAction();
			myController.getMap().selectCell(this);
			active = true;
			myOverlay.getStyleClass().clear();
			myOverlay.getStyleClass().add("activecell");
		}
	}

	@Override
	public void deactivate() {
		active = false;
		myOverlay.getStyleClass().clear();
		myOverlay.getStyleClass().add("inactivecell");
		blink(false);
	}

	private void handleActionInProgress() {
		myController.performActionInProgress(myController.getMap().getCellCoordinate(this));
	}

	@Override
	public void toggleFog(boolean fog) {
		// TODO Auto-generated method stub

	}

	@Override
	public void toggleHighlight(boolean highlight) {
		myOverlay.getStyleClass().clear();
		myOverlay.getStyleClass().add((highlight) ? ("highlightcell") : ("inactivecell"));
		blink(highlight);
	}

	private void blink(boolean on) {
		if (on) {
			myBlinker.play();
		} else {
			myBlinker.stop();
		}
	}

	// private void redraw(){
	// myObjects.keySet().forEach((i) -> redrawLayer(i));
	// }

	private void redrawLayer(int layer) {
		makeLayers(layer);
		int count = myObjects.get(layer).size();
		myLayerMap.get(layer).getChildren().clear();
		if (count > 0) {
			// I'm sorry. I'm really sorry.
			if (layer == 0)
				count = 1;
			double countPerRow = Math.ceil(Math.sqrt(count));
			for (int i = 0, total = 0; i < countPerRow; i++) {
				for (int j = 0; j < countPerRow; j++, total++) {
					if (total == count)
						break;
					myLayerMap.get(layer).add(renderImage(myObjects.get(layer).get(total), (mySize / countPerRow)), j,
							i);
				}
			}
		}
		setChanged();
		notifyObservers();
	}

	private ImageView renderImage(PlayerMapObjectInterface object, double size) {
		ImageView image = new ImageView(myController.getResource().getImage(object.getImageURI()));
		image.setFitWidth(size);
		image.setFitHeight(size);
		return image;
	}

	private void makeLayers(int layer) {
		if (!myLayerMap.containsKey(layer)) {
			for (int i = 0; i <= layer; i++) {
				if (myLayerMap.containsKey(i))
					continue;
				GridPane g = new GridPane();
				myLayerMap.put(i, g);
				myObjectLayer.getChildren().add(g);
			}
		}
	}

	public void addTemporaryOverlay(Node overlay, double duration) {

	}

	private List<String> getImageList() {
		return myObjects.values().stream().filter(l -> !l.isEmpty()).flatMap(l -> l.stream().map(o -> o.getImageURI()))
				.collect(Collectors.toList());
	}

	@Override
	public Map<Integer, List<PlayerMapObjectInterface>> getUnits() {
		return myObjects;
	}

	public Color getColor() {
		List<Color> myColors;
		if (!fogActive)
			myColors = getColorsByPlayer();
		else
			myColors = Arrays.asList(Color.BLACK);
		return ColorUtility.average(myColors);
	}

	private List<Color> getColorsByImage() {
		List<Color> myColors = getImageList().stream().map((s) -> myController.getResource().getImageColor(s))
				.collect(Collectors.toList());
		return myColors;
	}

	private List<Color> getColorsByPlayer() {
		List<Color> result = myObjects.values().stream().filter(l -> !l.isEmpty())
				.flatMap(l -> l.stream().map(o -> getObjectColors(o))).flatMap(l -> l.stream())
				.collect(Collectors.toList());
		return result;
	}

	private List<Color> getObjectColors(PlayerMapObjectInterface object) {
		if (object.getOwnerID() == -1) {
			return Arrays.asList(myController.getResource().getImageColor(object.getImageURI()));
		} else {
			List<Color> result = new ArrayList<>();
			Color c = Color.web(
					PlayerConfig.getResourceNumber(PlayerConfig.globalConfig(), "PlayerColor", object.getOwnerID()));
			for (int i = 0; i <= Integer.parseInt(myConfig.getString("PlayerMultiplier")); i++) {
				result.add(c);
			}
			return result;
		}
	}

}
