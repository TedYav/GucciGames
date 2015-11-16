package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public abstract class AbstractTab extends Tab implements ITab{
	protected List<String> allImagePaths = new ArrayList<String>();
	protected List<ImageView> allImageViews;
	protected List<Button> allImageButtons;
	protected VBox myVBox = new VBox(30);
	protected Button myAddButton = new Button("Add Custom");
	protected GridPane myGridPane = new GridPane();
	protected ContextMenu myContextMenu = new ContextMenu();
	protected IGuiGaeController myController;

	AbstractTab(IGuiGaeController controller) {
		myController = controller;
		allImageViews = new ArrayList<ImageView>();
		allImageButtons = new ArrayList<Button>();
		this.setClosable(false);
		createEmptyContent();
		createMenu();
	}
	
	protected void createMenu(){
		MenuItem item1 = new MenuItem("Edit");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Edit");
			}
		});
		MenuItem item2 = new MenuItem("Duplicate");
		item2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Duplicate");
			}
		});
		MenuItem item3 = new MenuItem("Remove");
		item3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Remove");
			}
		});
		myContextMenu.getItems().addAll(item1, item2, item3);
	}

	protected void createEmptyContent() {
		myGridPane.setVgap(20);
		myGridPane.setHgap(20);
		myGridPane.setPadding(new Insets(20, 20, 20, 20));

		myVBox.setPadding(new Insets(20, 20, 20, 20));
		myVBox.getChildren().addAll(myGridPane, myAddButton);
		
		this.setContent(myVBox);
	}

	protected void addImages() {
		int i=0;
		int j=0;
		for(String path : allImagePaths){
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
			ImageView imageView = new ImageView(image);
			allImageViews.add(imageView);

			imageView.setFitHeight(30);
			imageView.setFitWidth(30);

			myGridPane.add(imageView, i, j);
			if(i>4){ i=0;j++;}
			else{ i++;}
		}
	}

	protected void addImageHandler(){
		for(ImageView imageview : allImageViews) {
			imageview.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	                if (e.getButton() == MouseButton.SECONDARY) {
	                	myContextMenu.show(imageview, e.getScreenX(), e.getScreenY());
	                } else {
	                    System.out.println("No right click");
	                }
	            }
	        });
		}
	}
	
	protected void addDragDropListener(List<MapObjectType> listTypes){
		List<MapObjectType> currMapTypeList = listTypes;
		for(int i=0; i<allImageViews.size(); i++){
			ImageView imageview = allImageViews.get(i);
			imageview.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					int imageviewId = allImageViews.indexOf(imageview);
					MapObjectType currMapType = currMapTypeList.get(imageviewId);
					myController.setMapObjectTypeToMap(currMapType);
					myController.setCurrDraggedImage(imageview.getImage());
					event.consume();
				}
			});

		}
	}
	
	protected abstract void addAddButtonListener();
}
