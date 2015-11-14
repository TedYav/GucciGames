package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

/** Constructs a scene with a pannable Map background. */
public class GUIMap extends ScrollPane implements IMap, IGuiMap {
	private static final int GRID_SIZE = 10;

	private IGuiGaeController myController;
	private Pane myLayout;
	private ImageView myBackground;
	private DoubleProperty myCellSize;
	private Rectangle myMouseBound;

	public GUIMap(IGuiGaeController controller) {
		setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		setPannable(true);
		// center the scroll contents.
		setHvalue(getHmin() + (getHmax() - getHmin()) / 2);
		setVvalue(getVmin() + (getVmax() - getVmin()) / 2);

		myLayout = new Pane();
		setContent(myLayout);
		myBackground = new ImageView();
		myLayout.getChildren().setAll(myBackground);
		myBackground.fitWidthProperty().bind(myLayout.widthProperty());
		myBackground.fitHeightProperty().bind(myLayout.heightProperty());

		myCellSize = new SimpleDoubleProperty(getViewportBounds().getWidth() / GRID_SIZE);

		myCellSize.addListener((ch, oV, nV) -> System.out.println(nV));
		viewportBoundsProperty().addListener((ch, oV, nV) -> myCellSize.set(nV.getWidth() / GRID_SIZE));
		myLayout.setOnMouseMoved(e -> trackMouseMove(e.getX(),e.getY()));
		myLayout.setOnDragOver(e -> trackMouseMove(e.getX(),e.getY()));
		myLayout.setOnMouseExited(e->removeMouseBound());
		myLayout.setOnDragExited(e->removeMouseBound());
		
		myLayout.setOnDragDropped(e->System.out.println("Dropped"));
		myLayout.setOnDragDetected(e->System.out.println("Detected"));
		myLayout.setOnDragEntered(e->System.out.println("Entered"));
		myLayout.setOnDragDone(e->System.out.println("Done"));
		myLayout.setOnMouseReleased(e->System.out.println("Released"));
	}

	public void initGrid() {
		int width = 50, height = 10;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				new CellGUI(this, x, y);
			}
		}
	}

	public void setBackground(Image background) {
		myBackground.setImage(background);

	}

	@Override
	public void setMapObjectForPosition(CellGUI obj, Point2D pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMapObjectAtPosition(CellGUI obj, Point2D pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public DoubleProperty getCellSizeProperty() {
		return myCellSize;
	}

	@Override
	public void add(Node n) {
		myLayout.getChildren().add(n);
	}

	@Override
	public void remove(Node n) {
		myLayout.getChildren().remove(n);

	}

	private void trackMouseMove(double x,double y) {
		double size = myCellSize.get();
		double xt = x - x % size;
		double yt = y - y % size;
		if (myMouseBound == null) {
			myMouseBound = new Rectangle(xt, yt, size, size);
			myMouseBound.setFill(Color.TRANSPARENT);
			myMouseBound.setStroke(Color.YELLOW);
			myMouseBound.setStrokeWidth(1.5);
			myLayout.getChildren().add(myMouseBound);
		} else if (!myMouseBound.contains(x, y)) {
			myLayout.getChildren().remove(myMouseBound);
			myMouseBound = new Rectangle(xt, yt, size, size);
			myMouseBound.setFill(Color.TRANSPARENT);
			myMouseBound.setStroke(Color.YELLOW);
			myMouseBound.setStrokeWidth(2);
			myLayout.getChildren().add(myMouseBound);
		}
	}

	private void removeMouseBound() {
		if (myMouseBound != null) {
			myLayout.getChildren().remove(myMouseBound);
			myMouseBound = null;
		}
	}
}