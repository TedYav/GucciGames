// This entire file is part of my masterpiece.
// John Dai
package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;


/**
 * The class DisplayMapObjectImage is a sidebar GUI component that displays all the MapObjects at
 * the currently selected grid position in the MainMap.
 * It also allows the user to click on an image being displayed and setting the corresponding
 * MapObject as the currently active one.
 * 
 * @author John Dai, Ted Yavuzkurt
 */
public class DisplayMapObjectImage extends DisplayComponent
        implements ListChangeListener<PlayerMapObjectInterface> {
    private List<PlayerMapObjectInterface> mapObjectsOnCell;
    private Pane display;
    private Image buffer;
    private ImageView imgView;
    private ResourceBundle myBundle = PlayerConfig.load("components.Bar");
    private ResourceBundle myCssBundle = PlayerConfig.load(myBundle.getString("cssclass"));

    /**
     * Instantiates a new display map object image by calling DisplayComponent's constructor and
     * initializing variables.
     *
     * @param scene the scene
     * @param controller the controller
     */
    public DisplayMapObjectImage (GameScene scene, GameControllerInterface controller) {
        super(scene, controller);
        display = new FlowPane();
        display.getStyleClass().add(myCssBundle.getString("leftimageflowpane"));
        mapObjectsOnCell = new ArrayList<PlayerMapObjectInterface>();
        updateImages();
    }

    /**
     * Redraws the images based on the contents of mapObjectsOnCell.
     */
    public void updateImages () {
        display.getChildren().clear();
        mapObjectsOnCell.forEach(m -> initializeImage(m));
        if (display.getChildren().size() == 0) {
            showImagePlaceholder(myBundle.getString("imageplaceholder"));
        }
    }

    /**
     * Creates the ImageView from the MapObject, applies an action to it when clicked, and adds it
     * to be displayed.
     *
     * @param mapObject the MapObject
     */
    private void initializeImage (PlayerMapObjectInterface mapObject) {
        if (mapObject != null) {
            buffer = getController().getResource().getImage(mapObject.getImageURI());
        }
        imgView = createImageView(buffer);
        imgView.setOnMouseClicked(e -> {
            updateActiveMapObject(mapObject);
        });
        display.getChildren().add(imgView);
    }

    /**
     * Creates the image view given an Image and formats it.
     *
     * @param buffer the Image
     * @return the ImageView
     */
    private ImageView createImageView (Image buffer) {
        ImageView imageView = new ImageView(buffer);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(Integer.parseInt(myBundle.getString("imagefitwidth")));
        return imageView;
    }

    /**
     * Update active map object.
     *
     * @param mapObj the map obj
     */
    private void updateActiveMapObject (PlayerMapObjectInterface mapObj) {
        getController().setActiveMapObject(mapObj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * voogasalad_GucciGames.gameplayer.windows.mainwindow.components.WindowComponent#getParent()
     */
    @Override
    public Parent getParent () {
        return display;
    }

    /**
     * A private method that sets a default image if there is no MapObjects on the currently
     * selected grid location.
     *
     * @param url the String path to the image.
     */
    private void showImagePlaceholder (String url) {
        buffer = getController().getResource().getImage(url);
        imgView = createImageView(buffer);
        imgView.setOnMouseClicked(e -> {
            updateActiveMapObject(null);
        });
        display.getChildren().add(imgView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent#getListener()
     */
    @Override
    public ListChangeListener<PlayerMapObjectInterface> getListener () {
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javafx.collections.ListChangeListener#onChanged(javafx.collections.ListChangeListener.Change)
     */
    @Override
    public void onChanged (Change c) {
        while (c.next()) {
            List<PlayerMapObjectInterface> list = c.getList();
            if (list.size() > 0) {
                mapObjectsOnCell.clear();
                list.forEach(o -> mapObjectsOnCell.add(o));
                updateImages();
                setDefaultSelectedImage();
            }
        }
    }

    /**
     * Sets the default selected image.
     * In this case it chooses the last MapObject to have been added on the grid location.
     */
    private void setDefaultSelectedImage () {
        if (mapObjectsOnCell.size() > 0) {
            updateActiveMapObject(
                                  mapObjectsOnCell.stream().reduce( (u1, u2) -> u2)
                                          .orElseGet( () -> mapObjectsOnCell.get(0)));
        }
    }
}
