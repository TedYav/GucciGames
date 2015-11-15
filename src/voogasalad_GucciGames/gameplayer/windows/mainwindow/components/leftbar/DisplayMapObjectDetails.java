package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class DisplayMapObjectDetails  implements DisplayComponent{
    private ListView<String> list;
    public DisplayMapObjectDetails() {
        List<String> temp = new ArrayList<String>();
        temp.add("fasdf");
        list=new ListView<String>(FXCollections.observableList(temp));
    }
    public Node getNodeToDraw() {
        return list;
    }
}
