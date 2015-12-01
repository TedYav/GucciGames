package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.config.Config;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class DisplayMapObjectImage extends DisplayComponent implements ListChangeListener<PlayerMapObjectInterface>{
    private List<PlayerMapObjectInterface> mapObjectsOnCell;
    private FlowPane display;
    private Image buffer;
    private ImageView imgView;
    private ResourceBundle myBundle=Config.load("components.LeftBar");
    private ResourceBundle myCssBundle = Config.load(myBundle.getString("cssclass"));

    public DisplayMapObjectImage (GameControllerInterface controller) {
        super(controller);
        display=new FlowPane();
        display.getStyleClass().add(myCssBundle.getString("leftimageflowpane"));
        //display.setAlignment(Pos.CENTER);
        mapObjectsOnCell=new ArrayList<PlayerMapObjectInterface>();
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
            buffer = getMyController().requestImage(m.getImageURI());
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
        getMyController().setActiveMapObject(mapObj);
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
    public ListChangeListener<PlayerMapObjectInterface> getListener () {
        return this;
    }
    @Override
    public void updateDisplay () {
        return;
    }
    @Override
    public void onChanged (Change c) {
        while (c.next()) {
            List<PlayerMapObjectInterface> list = c.getList();
            if (list.size()>0){
                mapObjectsOnCell.clear();
                for (PlayerMapObjectInterface o: list){
                    mapObjectsOnCell.add(o);
                }
                updateImages();
                if (mapObjectsOnCell.size()>0) {
                    updateActiveMapObject(mapObjectsOnCell.stream().reduce((u1, u2) -> u2).orElseGet(()->mapObjectsOnCell.get(0)));
                }
            }
        }
    }
}
