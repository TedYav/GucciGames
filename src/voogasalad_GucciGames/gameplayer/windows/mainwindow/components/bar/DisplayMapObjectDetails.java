package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class DisplayMapObjectDetails extends DisplayComponent implements ListChangeListener<PlayerMapObjectInterface>, Observer{
    private ListView<String> listView;
    private List<String> attributeList;
    private ResourceBundle myBundle=PlayerConfig.load("components.LeftBar");

    public DisplayMapObjectDetails(GameControllerInterface controller) {
        super(controller);
        attributeList= new ArrayList<String>();
        attributeList.add(myBundle.getString("detailplaceholder"));
        listView=new ListView<String>(FXCollections.observableList(attributeList));
        getMyController().addActiveMOObserver(this);
    }
    public Node getNodeToDraw() {
        return listView;
    }
    @Override
    public void onChanged (Change c) {
        while (c.next()) {
            List<PlayerMapObjectInterface> list = c.getList();
            if (list.size()>0){
                attributeList.clear();
                listView.setItems(FXCollections.observableList(attributeList));
            }
            if (list.size()>0) {
                updateActiveMapObject(list.stream().reduce((u1, u2) -> u2).orElseGet(()->list.get(0)));
            }
        }
    }
    private void updateActiveMapObject(PlayerMapObjectInterface mapObj) {
        getMyController().setActiveMapObject(mapObj);
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

    @Override
    public ListChangeListener<PlayerMapObjectInterface> getListener () {
        return this;
    }
}
