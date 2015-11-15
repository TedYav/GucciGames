package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.PlayerMapObjectInterface;

public class DisplayMapObjectDetails  implements DisplayComponent, ListChangeListener<PlayerMapObjectInterface>{
    private ListView<String> list;
    private MapInterface myMap;
    private List<String> temp;
    public DisplayMapObjectDetails(MapInterface map) {
        temp= new ArrayList<String>();
        temp.add("fasdf");
        list=new ListView<String>(FXCollections.observableList(temp));
    }
    public Node getNodeToDraw() {
        return list;
    }
    @Override
    public void onChanged (Change c) {
        while (c.next()) {
            List<PlayerMapObjectInterface> list = c.getList();
            temp.clear();
            for (PlayerMapObjectInterface o: list){
                temp.add("afs");
            }
        }
    }
}
