package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.datastructures.TwoWayMap;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public abstract class ATab extends Tab {
	private static final int GAP = 20;
	private static final int WIDTH = 4;
	protected final Pane myPane = new Pane();
	protected final GridPane myGridPane = new GridPane();
	protected final AGuiGaeController myController;
	protected ObservableList<MapObjectType> myTypeList;
	protected final TwoWayMap<MapObjectType, ImageView> myMap = new TwoWayMap<>();
	protected List<ImageView> myImageViews = new ArrayList<>();
	protected Button myAddButton = new Button("Add Custom");
	protected ContextMenu myContextMenu;

	private final SideBar mySideBar;
	private Rectangle myBoundBox;
	private ImageView myTrace;
	private MapObjectType mySelectedType;

	ATab(SideBar bar) {
		mySideBar = bar;
		myController = bar.getController();
		myContextMenu = new SideBarMenu(myController);
		setClosable(false);
		setLayout();
		myAddButton.setOnAction(e -> addNewTypeDialog(e));
	}

	private void setLayout() {
		myGridPane.setVgap(GAP);
		myGridPane.setHgap(GAP);
		Insets padding = new Insets(GAP,GAP,GAP,GAP);
		myGridPane.setPadding(padding);
		VBox container = new VBox(myGridPane, myAddButton);
		container.setPadding(padding);
		myPane.getChildren().add(container);
		container.maxWidthProperty().bind(myPane.widthProperty());
		container.minWidthProperty().bind(myPane.widthProperty());
		container.maxHeightProperty().bind(myPane.heightProperty());
		this.setContent(myPane);
		myPane.setOnMouseMoved(e -> trackMouse(e));
		myPane.setOnMouseExited(e -> deleteTrace());
		myPane.setOnMouseEntered(e -> trackMouse(e));

	}

	protected void init(ObservableList<MapObjectType> list) {
		myTypeList = list;
		list.forEach(e -> addType(e));
		list.addListener((ListChangeListener.Change<? extends MapObjectType> change) -> {
			change.next();
			change.getAddedSubList().forEach(e -> addType(e));
			change.getRemoved().forEach(e -> deleteType(e));
		});
	}

	private void addType(MapObjectType type) {
		ImageView imageView = myController.getMapObjectImage(type);
		
		imageView.setFitHeight(2*GAP);
		imageView.setFitWidth(2*GAP);

		imageView.setOnMouseClicked(e -> mouseClicked(imageView, e));
		imageView.setOnDragDetected(e -> dragDetected(imageView, e));
		myMap.put(type, imageView);
		myImageViews.add(imageView);
		int index = myImageViews.size() - 1;
		myGridPane.add(imageView, index % WIDTH, index / WIDTH);
	}

	private void dragDetected(ImageView imageView, MouseEvent e) {
		if(myTrace != null)
			myTrace.setVisible(false);
		/* allow any transfer mode */
		Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
		/* put a string on dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putImage(imageView.getImage());
		db.setContent(content);
		myController.setDragType(myMap.getKey(imageView));
		e.consume();
	}

	@SuppressWarnings("static-access")
	private void deleteType(MapObjectType type) {
		ImageView imageView = myMap.remove(type);
		int index = myImageViews.indexOf(imageView);
		myGridPane.getChildren().remove(imageView);
		myImageViews.remove(index);
		for (int i = index; i < myImageViews.size(); i++) {
			myGridPane.setColumnIndex(myImageViews.get(i), index % WIDTH);
			myGridPane.setRowIndex(myImageViews.get(i), index / WIDTH);
		}
	}

	protected abstract void addNewTypeDialog(ActionEvent e);

	private void mouseClicked(ImageView source, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			select(source);
		} else if (e.getButton() == MouseButton.SECONDARY) {
			myContextMenu.show(source, e.getSceneX(), e.getSceneY());
		}
		e.consume();
	}

	@SuppressWarnings("static-access")
	private void select(ImageView source) {
		MapObjectType curr = myMap.getKey(source);
		setTrace(myController.getMapObjectImage(curr));
		if (curr != mySideBar.getCurrSelection()) {
			mySideBar.setCurrSelection(this, curr);
			myController.setSelectedType(curr);
			myBoundBox = new Rectangle(source.getFitWidth(), source.getFitHeight());
			myBoundBox.setStroke(Color.RED);
			myBoundBox.setStrokeWidth(2);
			myBoundBox.setFill(Color.TRANSPARENT);
			myBoundBox.setMouseTransparent(true);
			myGridPane.add(myBoundBox, myGridPane.getColumnIndex(source), myGridPane.getRowIndex(source));
		} else {
			mySideBar.setCurrSelection(this, null);
			deselect();
		}
	}

	protected void deselect() {
		if (myBoundBox != null) {
			myController.setSelectedType(null);
			myGridPane.getChildren().remove(myBoundBox);
			myBoundBox = null;
			myPane.getChildren().remove(myTrace);
			myTrace = null;
		}
	}

	private void setTrace(ImageView img) {
		if (myTrace != img) {
			myPane.getChildren().remove(myTrace);
			myTrace = img;
			myTrace.setFitHeight(30);
			myTrace.setFitWidth(30);
			myTrace.setOpacity(0.5);
			myTrace.setMouseTransparent(true);
			myPane.getChildren().add(myTrace);
		}
	}

	private void deleteTrace() {
		myPane.getChildren().remove(myTrace);
		myTrace = null;
	}

	private void trackMouse(MouseEvent e) {
		if (myController.getSelectedType() == null) {
			deleteTrace();
			e.consume();
			return;
		} else {
			if (mySelectedType != myController.getSelectedType()) {
				mySelectedType = myController.getSelectedType();
				setTrace(myController.getMapObjectImage(mySelectedType));
			}
			if (myTrace == null)
				setTrace(myController.getMapObjectImage(mySelectedType));
			myTrace.setX(e.getX() - 15);
			myTrace.setY(e.getY() - 15);
		}
	}

}
