package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.datastructures.TwoWayMap;
import voogasalad_GucciGames.helpers.GameResourceManagerToGAE;

class ImageBrowseDialog extends Dialog<String> {
	private static int SIZE = 40;
	private GridPane myGrid;
	private final GameResourceManagerToGAE myResManager;
	private final IntegerProperty mySelectImg = new SimpleIntegerProperty(-1);
	private final TwoWayMap<Integer, ImageView> myMap = new TwoWayMap<>();
	private Rectangle myBound = null;
	private final int imgPerRow;

	ImageBrowseDialog(GameResourceManagerToGAE resManager, String type) {
		myResManager = resManager;
		setTitle("Image Browser");
		setHeaderText("Select an image:");
		ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);
		addGrid();
		
		List<String> images = resManager.getImages(type);
		imgPerRow = (int)Math.sqrt(images.size());
		addImgs(images);

		Node okButton = getDialogPane().lookupButton(ok);
		okButton.setDisable(mySelectImg.get() == -1);
		mySelectImg.addListener((ch, oV, nV) -> okButton.setDisable(mySelectImg.get() == -1));
		Platform.runLater(() -> okButton.requestFocus());

		setResultConverter(dialogButton -> {
			if (dialogButton == ok) {
				return images.get(mySelectImg.get());
			}
			return null;
		});
	}

	private void addImgs(List<String> images) {
		for (int i = 0; i < images.size(); i++) {
			ImageView img = addImg(images.get(i));
			myMap.put(i, img);
			myGrid.add(img, i % imgPerRow, i / imgPerRow);
		}

	}

	private ImageView addImg(String URI) {
		ImageView img = new ImageView(myResManager.getImage(URI));
		img.setFitHeight(SIZE);
		img.setFitWidth(SIZE);
		img.setOnMouseClicked(e -> onClicked(img, e));
		return img;
	}

	private void onClicked(ImageView source, MouseEvent e) {
		if(mySelectImg.get()!=-1){
			removeBound();
			if(myMap.getKey(source) == mySelectImg.get()){
				mySelectImg.set(-1);
				return;
			}
		}
		mySelectImg.set(myMap.getKey(source));
		addBound(mySelectImg.get());
	}
	
	private void addBound(int index) {
		myBound = new Rectangle(SIZE,SIZE);
		myBound.setFill(Color.rgb(0, 0, 255, 0.4));
		myBound.setMouseTransparent(true);
		myGrid.add(myBound, index % imgPerRow, index / imgPerRow);
	}

	private void removeBound() {
		if (myBound != null) {
			myGrid.getChildren().remove(myBound);
			myBound = null;
		}
	}

	private void addGrid() {
		myGrid = new GridPane();
		myGrid.setHgap(10);
		myGrid.setVgap(10);
		myGrid.setPadding(new Insets(20, 10, 10, 10));
		getDialogPane().setContent(myGrid);
	}

}
