package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class GameStatsDisplay implements DisplayComponent {
    private ListView<String> list;
    private List<String> temp;
    private ObservableList<String> oTemp;
    private GameControllerInterface myController;
    
    public GameStatsDisplay(GameControllerInterface controller) {
        temp = new ArrayList<String>();
        oTemp = FXCollections.observableList(temp);
        temp.add("gamestats");
        list=new ListView<String>(oTemp);
        myController=controller;
        update();
    }
    
    public void update() {
        oTemp.clear();
        oTemp.add("Game won: "+myController.getEngine().getGameParameters().gameWon());
        oTemp.add("Whose turn: "+myController.getEngine().getGameParameters().whoseTurn());
        //list=new ListView<String>(oTemp);
        System.out.println(oTemp.get(0));
    }
    
    @Override
    public Node getNodeToDraw() {
        return list;
    }

}
