package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.PlayerMapObjectInterface;

public class DisplayMapObjectDetails  implements DisplayComponent, ListChangeListener<PlayerMapObjectInterface>{
    private ListView<String> list;
    private MapInterface myMap;
    private List<String> temp;
    private List<String> imageUrls;
    private DisplayMapObjectImage imageDisplay;
    private VBox display;
    private GameControllerInterface myController;
    public DisplayMapObjectDetails(MapInterface map, GameControllerInterface controller) {
        temp= new ArrayList<String>();
        temp.add("fasdf");
        list=new ListView<String>(FXCollections.observableList(temp));
        myController=controller;
        imageUrls=new ArrayList<String>();
        imageDisplay = new DisplayMapObjectImage(imageUrls, myController);
        display = new VBox();
        display.getChildren().add(imageDisplay.getNodeToDraw());
        display.getChildren().add(list);
    }
    public Node getNodeToDraw() {
        return display;
    }
    @Override
    public void onChanged (Change c) {
        List<String> contents;
        while (c.next()) {
            List<PlayerMapObjectInterface> list = c.getList();
            temp.clear();
            imageUrls.clear();
            for (PlayerMapObjectInterface o: list){
                temp.add(o.getImageURI());
                contents=o.getActionNames();
                for (String s: contents) {
                    temp.add(s);
                }
                imageUrls.add(o.getImageURI());
            }
            imageDisplay.updateImages();
        }
    }
}
