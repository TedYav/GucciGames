package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;

public class LeftBar extends WindowComponent{
    private VBox container;
    private double spacing = 5;
    private MapInterface myMap;
    private ResourceBundle myBundle=ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.LeftBar");

    private DisplayMapObjectDetails objectDetails;
    private DisplayChat chatDisplay;
    private ResourceBundle myCssBundle = ResourceBundle.getBundle(myBundle.getString("cssclass"));

    public LeftBar (GameScene scene, GameControllerInterface controller) {
        super(scene, controller);
        container = new VBox(spacing);
        initializeData();
        myMap=myController.getMap();
    }

    private void initializeData() {
        objectDetails = new DisplayMapObjectDetails(myController);//TODO: create in properties file?
        chatDisplay = new DisplayChat();
        container.getChildren().add(objectDetails.getNodeToDraw());
        container.getChildren().add(chatDisplay.getNodeToDraw());
        container.getStyleClass().add(myCssBundle.getString("LeftVBox"));
        container.setPrefWidth(Double.parseDouble(myCssBundle.getString("leftprefwidth")));
    }

    @Override
    public Parent getParent () {
        return container;
    }
    
    public ListChangeListener requestListener() {
        return objectDetails;
    }

}
