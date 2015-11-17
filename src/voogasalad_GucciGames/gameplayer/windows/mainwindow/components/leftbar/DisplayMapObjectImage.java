package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class DisplayMapObjectImage implements DisplayComponent{
    private List<PlayerMapObjectInterface> mapObjectsOnCell;
    private FlowPane display;
    private GameControllerInterface myController;
    private Image buffer;
    private ImageView imgView;

    public DisplayMapObjectImage (List<PlayerMapObjectInterface> imageUrls, GameControllerInterface controller) {
        display=new FlowPane();
        mapObjectsOnCell=imageUrls;
        myController=controller;
        updateImages();
    }
        public void updateImages() {
            display.getChildren().clear();
            for (PlayerMapObjectInterface m: mapObjectsOnCell) {
                initializeImage(m);
            }
            if (display.getChildren().size()==0) {
                debugInitializeImage("player/images/leftbar-image-placeholder.jpg");
                debugInitializeImage("player/images/leftbar-image-placeholder-2.gif");
            }
        }
        private void initializeImage(PlayerMapObjectInterface m) {
            if (m!=null) {
                buffer = myController.requestImage(m.getImageURI());
            }
            imgView=new ImageView(buffer);
            imgView.setPreserveRatio(true);
            imgView.setFitWidth(100);
            imgView.setOnMouseClicked(e->{
                updateActiveMapObject(m);
            });
            display.getChildren().add(imgView);
        }
        private void updateActiveMapObject(PlayerMapObjectInterface mapObj) {
            myController.setActiveMapObject(mapObj);
        }
        @Override
        public Node getNodeToDraw() {
                return display;
        }
        
        
        /**
         * Dev method
         * @param url
         */
        private void debugInitializeImage(String url) {
                buffer = new Image(url,0,100,true,false,true);
            imgView=new ImageView(buffer);
            imgView.setOnMouseClicked(e->{
                updateActiveMapObject(null);
            });
            display.getChildren().add(imgView);
        }
}
