package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class DisplayMapObjectImage extends DisplayComponent implements ListChangeListener<PlayerMapObjectInterface>{
    private List<PlayerMapObjectInterface> mapObjectsOnCell;
    private FlowPane display;
    private Image buffer;
    private ImageView imgView;
    private ResourceBundle myBundle=PlayerConfig.load("components.Bar");
    private ResourceBundle myCssBundle = PlayerConfig.load(myBundle.getString("cssclass"));

    public DisplayMapObjectImage (GameScene scene, GameControllerInterface controller) {
        super(scene,controller);
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
            showImagePlaceholder(myBundle.getString("imageplaceholder"));
        }
    }
    private void initializeImage(PlayerMapObjectInterface m) {
        if (m!=null) {
            buffer = getController().getResource().getImage(m.getImageURI());
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
        getController().setActiveMapObject(mapObj);
    }

    @Override
    public Parent getParent() {
        return display;
    }

    /**
     * Dev method
     * @param url
     */
    private void showImagePlaceholder(String url) {
        //buffer = new Image(url,Integer.parseInt(myBundle.getString("imagefitwidth")),0,true,false,true);
        buffer = getController().getResource().getImage(url);
    	imgView=new ImageView(buffer);
    	imgView.setFitWidth(Integer.parseInt(myBundle.getString("imagefitwidth")));
    	imgView.setPreserveRatio(true);
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
