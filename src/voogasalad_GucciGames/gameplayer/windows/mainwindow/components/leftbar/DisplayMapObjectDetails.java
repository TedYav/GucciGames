package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;
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

public class DisplayMapObjectDetails  implements DisplayComponent, ListChangeListener<PlayerMapObjectInterface>, Observer{
    private ListView<String> listView;
    private MapInterface myMap;
    private List<String> temp;
    private List<PlayerMapObjectInterface> mapObjectsOnCell;
    private DisplayMapObjectImage imageDisplay;
    private VBox display;
    private GameControllerInterface myController;
    public DisplayMapObjectDetails(MapInterface map, GameControllerInterface controller) {
        temp= new ArrayList<String>();
        temp.add("fasdf");
        listView=new ListView<String>(FXCollections.observableList(temp));
        myController=controller;
        mapObjectsOnCell=new ArrayList<PlayerMapObjectInterface>();
        imageDisplay = new DisplayMapObjectImage(mapObjectsOnCell, myController);
        display = new VBox();
        display.getChildren().add(imageDisplay.getNodeToDraw());
        display.getChildren().add(listView);

        myController.addMOObserver(this);
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
            mapObjectsOnCell.clear();
            for (PlayerMapObjectInterface o: list){
                //temp.add(o.getImageURI());
//                contents=o.getAttributes();
//                if (contents!=null) {
//                    for (String s: contents) {
//                        temp.add(s);
//                    }
//                }
                mapObjectsOnCell.add(o);
            }
            imageDisplay.updateImages();
            listView.setItems(FXCollections.observableList(temp));
        }
    }
    @Override
    public void update (Observable o, Object arg) {
        if (arg!=null) {
            System.out.println("ho");
            PlayerMapObjectInterface mapObj=(PlayerMapObjectInterface)arg;
            List<String> list = mapObj.getActionNames();
            for (String s: list) {
                temp.add(s);
            }
            listView.setItems(FXCollections.observableList(temp));
        }
    }
}
