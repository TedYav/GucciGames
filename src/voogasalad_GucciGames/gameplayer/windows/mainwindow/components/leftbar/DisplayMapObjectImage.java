package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class DisplayMapObjectImage implements DisplayComponent{
    private List<PlayerMapObjectInterface> mapObjectsOnCell;
    private FlowPane display;
    private GameControllerInterface myController;
    private Image buffer;
    private ImageView imgView;
    private ResourceBundle myBundle=ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.LeftBar");
    private ResourceBundle myCssBundle = ResourceBundle.getBundle(myBundle.getString("cssclass"));

    public DisplayMapObjectImage (List<PlayerMapObjectInterface> imageUrls, GameControllerInterface controller) {
        display=new FlowPane();
        display.getStyleClass().add(myCssBundle.getString("leftimageflowpane"));
        //display.setAlignment(Pos.CENTER);
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
            showImagePlaceholder("player/images/leftbar-image-placeholder.jpg");
        }
    }
    private void initializeImage(PlayerMapObjectInterface m) {
        if (m!=null) {
            buffer = myController.requestImage(m.getImageURI());
        }
        imgView=new ImageView(buffer);
        imgView.setPreserveRatio(true);
        imgView.setFitWidth(Integer.parseInt(myBundle.getString("imagefitwidth")));
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
    private void showImagePlaceholder(String url) {
        buffer = new Image(url,Integer.parseInt(myBundle.getString("imagefitwidth")),0,true,false,true);
        imgView=new ImageView(buffer);
        imgView.setOnMouseClicked(e->{
            updateActiveMapObject(null);
        });
        display.getChildren().add(imgView);
    }
    @Override
    public boolean listensToMap () {
        return false;
    }
    @Override
    public ListChangeListener getListener () {
        return null;
    }
    @Override
    public void update () {
        return;
    }
}
