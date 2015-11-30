package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class GameStatsDisplay implements DisplayComponent {
    private ListView<String> listView;
    private List<String> stats;
    private ObservableList<String> observeStats;
    private GameControllerInterface myController;
    private ResourceBundle myBundle=ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.RightBar");

    public GameStatsDisplay(GameControllerInterface controller) {
        stats = new ArrayList<String>();
        observeStats = FXCollections.observableList(stats);
        listView=new ListView<String>(observeStats);
        myController=controller;
        update();
    }

    @Override
    public void update() {
        observeStats.clear();
        observeStats.add(myBundle.getString("statswin")+myController.getEngine().getGameParameters().gameWon());
        observeStats.add(myBundle.getString("statsturn")+myController.getEngine().getGameParameters().whoseTurn());
    }

    @Override
    public Node getNodeToDraw() {
        return listView;
    }

    @Override
    public boolean listensToMap () {
        return false;
    }

    @Override
    public ListChangeListener getListener () {
        return null;
    }

}
