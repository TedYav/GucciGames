package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class GameStatsDisplay implements DisplayComponent {
    private ListView<String> list;
    
    public GameStatsDisplay() {
        List<String> temp = new ArrayList<String>();
        temp.add("gamestats");
        list=new ListView<String>(FXCollections.observableList(temp));
    }
    
    @Override
    public Node getNodeToDraw() {
        return list;
    }

}
