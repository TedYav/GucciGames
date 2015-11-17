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
    private ListView<String> list;
    private MapInterface myMap;
    private List<String> temp;
    private List<PlayerMapObjectInterface> mapObjectsOnCell;
    private DisplayMapObjectImage imageDisplay;
    private VBox display;
    private GameControllerInterface myController;
    public DisplayMapObjectDetails(MapInterface map, GameControllerInterface controller) {
        temp= new ArrayList<String>();
        temp.add("fasdf");
        list=new ListView<String>(FXCollections.observableList(temp));
        myController=controller;
        mapObjectsOnCell=new ArrayList<PlayerMapObjectInterface>();
        imageDisplay = new DisplayMapObjectImage(mapObjectsOnCell, myController);
        display = new VBox();
        display.getChildren().add(imageDisplay.getNodeToDraw());
        display.getChildren().add(list);
        
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
                temp.add(o.getImageURI());
                contents=o.getActionNames();
                for (String s: contents) {
                    temp.add(s);
                }
                mapObjectsOnCell.add(o);
            }
            imageDisplay.updateImages();
        }
    }
    @Override
    public void update (Observable o, Object arg) {
        System.out.println("ho");
        if (arg!=null && arg.getClass().equals(PlayerMapObjectInterface.class)) {
            PlayerMapObjectInterface mapObj=(PlayerMapObjectInterface)arg;
            
              Map<String, String> list = mapObj.getAttributes();
            for (String s: list.keySet()) {
                temp.add(s);
            } 
            
            
        }
    }
}
