package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameplayer.config.Config;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class GameStatsDisplay extends DisplayComponent {
    private ListView<String> listView;
    private List<String> stats;
    private ObservableList<String> observeStats;
    private ResourceBundle myBundle=Config.load("components.RightBar");

    public GameStatsDisplay(GameControllerInterface controller) {
        super(controller);
        stats = new ArrayList<String>();
        observeStats = FXCollections.observableList(stats);
        listView=new ListView<String>(observeStats);
        updateDisplay();
    }

    @Override
    public void updateDisplay() {
        observeStats.clear();
        observeStats.add(myBundle.getString("statswin")+getMyController().getEngine().getGameParameters().gameWon());
        observeStats.add(myBundle.getString("statsturn")+getMyController().getEngine().getGameParameters().whoseTurn());
    }

    @Override
    public Node getNodeToDraw() {
        return listView;
    }

}
