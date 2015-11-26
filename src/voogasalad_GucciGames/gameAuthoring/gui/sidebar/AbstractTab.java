package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public abstract class AbstractTab extends Tab {
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
			imageView.setFitHeight(30);
			imageView.setFitWidth(30);
			allImageViews.add(imageView);
			
			Button buttonImage = new Button();
			buttonImage.setGraphic(imageView);
			allImageButtons.add(buttonImage);

			myGridPane.add(buttonImage, i, j);
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
	
	protected void addDragDropListener(List<MapObject> listTypes){
		List<MapObject> currMapTypeList = listTypes;
		for(int i=0; i<allImageButtons.size(); i++){
			ImageView imageview = allImageViews.get(i);
			Button imageButton = allImageButtons.get(i);
			imageButton.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					int imageviewId = allImageViews.indexOf(imageview);
					MapObject currMapType = currMapTypeList.get(imageviewId);
					myController.setMapObjectTypeToMap(currMapType);
					myController.setCurrDraggedImage(imageview.getImage());
					
//					Image currImage = new Image(getClass().getClassLoader().getResourceAsStream(allImagePaths.get(imageviewId)));
//					ImageView mouseImage = new ImageView(imageview.getImage());
//					mouseImage.setFitHeight(30);
//					mouseImage.setFitWidth(30);
//					myVBox.getChildren().add(mouseImage);
//					addImageUnderMouse(mouseImage);
					
					
					event.consume();
				}
			});
//			imageButton.setOnMousePressed(new EventHandler<MouseEvent>() {
//				public void handle(MouseEvent event) {
//					int imageviewId = allImageViews.indexOf(imageview);
//					MapObjectType currMapType = currMapTypeList.get(imageviewId);
//					myController.setMapObjectTypeToMap(currMapType);
//					myController.setCurrDraggedImage(imageview.getImage());
//					
//					Image currImage = new Image(getClass().getClassLoader().getResourceAsStream(allImagePaths.get(imageviewId)));
//					ImageView mouseImage = new ImageView(currImage);
//					mouseImage.setFitHeight(30);
//					mouseImage.setFitWidth(30);
//					
//					
//					event.consume();
//				}
//			});

		}
	}
	
//	private void addImageUnderMouse(ImageView imgView){
//		myVBox.setOnMouseMoved(new EventHandler<MouseEvent>() {
//				public void handle(MouseEvent event) {
//					System.out.println("hi");
//					imgView.setX(event.getScreenX());
//					imgView.setY(event.getScreenY());
//					System.out.println(event.getScreenX()+" "+event.getSceneY());
//				}
//			});
//	}
	
	protected abstract void addAddButtonListener();
}
