package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameplayer.datastructures.TwoWayMap;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class ATab extends Tab {
	private static final int WIDTH = 4;
	protected final Pane myPane = new Pane();
	protected final GridPane myGrid = new GridPane();
	protected ObservableList<MapObjectType> myTypeList;
	protected TwoWayMap<MapObjectType, ImageView> myMap = new TwoWayMap<>();
	protected List<ImageView> myImageViews = new ArrayList<>();
	protected Button myAddButton = new Button("Add Custom");
	protected ContextMenu myContextMenu;
	protected AGuiGaeController myController;
	private SideBar mySideBar;
	private Rectangle myBoundBox;
	private ImageView myTrace;

	ATab(SideBar bar) {
		mySideBar = bar;
		myController = bar.getController();
		myContextMenu = new SideBarMenu(myController);
		setClosable(false);
		setLayout();
		myAddButton.setOnAction(e->addNewTypeDialog(e));
	}

	private void setLayout() {
		myGrid.setVgap(20);
		myGrid.setHgap(20);
		Insets padding = new Insets(20, 20, 20, 20);
		myGrid.setPadding(padding);
		VBox container = new VBox(myGrid, myAddButton);
		container.setPadding(padding);
		myPane.getChildren().add(container);
		container.maxWidthProperty().bind(myPane.widthProperty());
		container.minWidthProperty().bind(myPane.widthProperty());
		container.maxHeightProperty().bind(myPane.heightProperty());
		this.setContent(myPane);
		myPane.setOnMouseMoved(e->trackMouse(e));
		myPane.setOnMouseExited(e->myTrace.setVisible(false));
		myPane.setOnMouseEntered(e->myTrace.setVisible(myBoundBox!=null));
		myTrace = new ImageView();
		myTrace.setVisible(false);
		myTrace.setFitHeight(30);
		myTrace.setFitWidth(30);
		myTrace.setOpacity(0.5);
		myTrace.setMouseTransparent(true);
		myPane.getChildren().add(myTrace);
	}

	protected void init(ObservableList<MapObjectType> list) {
		myTypeList = list;
		list.forEach(e -> addType(e));
		list.addListener((ListChangeListener.Change<? extends MapObjectType> change) -> {
			change.next();
			change.getAddedSubList().forEach(e->addType(e));
			change.getRemoved().forEach(e->deleteType(e));
		});
	}

	private void addType(MapObjectType type) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(type.getImagePath()));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(30);
		imageView.setFitWidth(30);
		imageView.setOnMouseClicked(e->mouseClicked(imageView, e));
		myMap.put(type, imageView);
		myImageViews.add(imageView);
		int index = myImageViews.size() - 1;
		myGrid.add(imageView, index % WIDTH, index / WIDTH);
	}

	@SuppressWarnings("static-access")
	private void deleteType(MapObjectType type) {
		ImageView imageView = myMap.remove(type);
		int index = myImageViews.indexOf(imageView);
		myGrid.getChildren().remove(imageView);
		myImageViews.remove(index);
		for(int i=index;i<myImageViews.size();i++){
			myGrid.setColumnIndex(myImageViews.get(i), index % WIDTH);
			myGrid.setRowIndex(myImageViews.get(i), index / WIDTH);
		}
	}

	protected abstract void addNewTypeDialog(ActionEvent e);
	
	private void mouseClicked(ImageView source, MouseEvent e){
		if(e.getButton()==MouseButton.PRIMARY){
			select(source);
		}else if(e.getButton()==MouseButton.SECONDARY){
			myContextMenu.show(source,e.getSceneX(),e.getSceneY());
		}
		e.consume();
	}

	@SuppressWarnings("static-access")
	private void select(ImageView source) {
		MapObjectType curr = myMap.getKey(source);
		if(curr!=mySideBar.getCurrSelection()){
			mySideBar.setCurrSelection(this,curr);
			myController.setMapObjectTypeToMap(curr);
			myController.setCurrDraggedImage(source.getImage());
			myBoundBox = new Rectangle(source.getFitWidth(),source.getFitHeight());
			myBoundBox.setStroke(Color.RED);
			myBoundBox.setStrokeWidth(2);
			myBoundBox.setFill(Color.TRANSPARENT);
			myBoundBox.setMouseTransparent(true);
			myGrid.add(myBoundBox, myGrid.getColumnIndex(source), myGrid.getRowIndex(source));
		}else{
			mySideBar.setCurrSelection(this,null);
			deselect();
		}
	}

	protected void deselect() {
		if(myBoundBox!=null){
			myController.setMapObjectTypeToMap(null);
			myController.setCurrDraggedImage(null);
			myGrid.getChildren().remove(myBoundBox);
			myBoundBox = null;
			myTrace.setImage(null);
		}	
	}
	
	private void trackMouse(MouseEvent e){
		if(myController.getCurrSelectedImage()==null){
			if(myTrace.isVisible()){
				myTrace.setVisible(false);
			}
			e.consume();
			return;
		}
		if(myTrace.getImage()!=myController.getCurrSelectedImage()){
			myTrace.setImage(myController.getCurrSelectedImage());
			myTrace.setVisible(true);
		}
		myTrace.setX(e.getX()-15);
		myTrace.setY(e.getY()-15);
	}

}
