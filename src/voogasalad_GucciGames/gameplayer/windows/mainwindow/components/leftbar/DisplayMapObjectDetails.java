package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class DisplayMapObjectDetails implements DisplayComponent, ListChangeListener<PlayerMapObjectInterface>, Observer{
    private ListView<String> listView;
    private List<String> attributeList;
    private List<PlayerMapObjectInterface> mapObjectsOnCell;
    private DisplayMapObjectImage imageDisplay;
    private VBox display;
    private GameControllerInterface myController;
    private ResourceBundle myBundle=ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.LeftBar");

    public DisplayMapObjectDetails(GameControllerInterface controller) {
        attributeList= new ArrayList<String>();
        attributeList.add(myBundle.getString("detailplaceholder"));
        listView=new ListView<String>(FXCollections.observableList(attributeList));
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
            if (list.size()>0){
            attributeList.clear();
            mapObjectsOnCell.clear();
            for (PlayerMapObjectInterface o: list){
                mapObjectsOnCell.add(o);
            }
            imageDisplay.updateImages();
            if (mapObjectsOnCell.size()>0) {
                updateActiveMapObject(mapObjectsOnCell.stream().reduce((u1, u2) -> u2).orElseGet(()->mapObjectsOnCell.get(0)));
            }
            listView.setItems(FXCollections.observableList(attributeList));
            }
        }
    }
    @Override
    public void update (Observable o, Object arg) {
    	 if (arg!=null) {
             PlayerMapObjectInterface mapObj=(PlayerMapObjectInterface)arg;
             Map<String,String> map = mapObj.getAttributeStrings();
             attributeList.clear();
             attributeList.add(mapObj.getName());
             for (String s: map.keySet()) {
                 attributeList.add(map.get(s));
             }
             listView.setItems(FXCollections.observableList(attributeList));
         }
    }
    private void updateActiveMapObject(PlayerMapObjectInterface mapObj) {
        myController.setActiveMapObject(mapObj);
    }
    @Override
    public boolean listensToMap () {
        return true;
    }
    @Override
    public ListChangeListener getListener () {
        return this;
    }
    @Override
    public void update () {
        return;
    }
}
