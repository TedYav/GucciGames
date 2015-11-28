package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class DisplayMapObjectDetails  implements DisplayComponent, ListChangeListener<PlayerMapObjectInterface>, Observer{
    private ListView<String> listView;
    private List<String> temp;
    private List<PlayerMapObjectInterface> mapObjectsOnCell;
    private DisplayMapObjectImage imageDisplay;
    private VBox display;
    private GameControllerInterface myController;
    public DisplayMapObjectDetails(GameControllerInterface controller) {
        temp= new ArrayList<String>();
        temp.add("(Select a cell)");
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
        while (c.next()) {
            List<PlayerMapObjectInterface> list = c.getList();
            temp.clear();
            mapObjectsOnCell.clear();
            for (PlayerMapObjectInterface o: list){
                mapObjectsOnCell.add(o);
            }
            imageDisplay.updateImages();
            if (mapObjectsOnCell.size()>0) {
                updateActiveMapObject(mapObjectsOnCell.stream().reduce((u1, u2) -> u2).orElseGet(()->mapObjectsOnCell.get(0)));
            }
            listView.setItems(FXCollections.observableList(temp));
        }
    }
    @Override
    public void update (Observable o, Object arg) {
    	 if (arg!=null) {
             PlayerMapObjectInterface mapObj=(PlayerMapObjectInterface)arg;
             Map<String,String> map = mapObj.getAttributeStrings();
             temp.clear();
             temp.add(mapObj.getName());
             for (String s: map.keySet()) {
                 temp.add(s+": "+map.get(s));
             }
             listView.setItems(FXCollections.observableList(temp));
         }
    }
    private void updateActiveMapObject(PlayerMapObjectInterface mapObj) {
        myController.setActiveMapObject(mapObj);
    }
}
