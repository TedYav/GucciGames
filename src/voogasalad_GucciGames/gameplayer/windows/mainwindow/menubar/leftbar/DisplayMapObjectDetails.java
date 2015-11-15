package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.leftbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;

public class DisplayMapObjectDetails  implements MiniDisplayComponent, Observer{
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
    public void update (Observable arg0, Object arg1) {
        if (arg0.getClass()==null) {
            temp.clear();
            temp.add("afs");
        }
    }
}
