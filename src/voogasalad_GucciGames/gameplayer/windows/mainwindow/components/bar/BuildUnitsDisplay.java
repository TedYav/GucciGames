package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class BuildUnitsDisplay extends DisplayComponent implements Observer{
    private PlayerMapObjectInterface activeMapObject;
    private ListView<Button> buttons;
    private List<Button> baseButtons;
    public BuildUnitsDisplay (GameScene scene, GameControllerInterface controller) {
        super(scene,controller);
        getMyController().addActiveMOObserver(this);
        baseButtons = new ArrayList<Button>();
        buttons = new ListView<Button>(FXCollections.observableList(baseButtons));
    }

    @Override
    public Parent getParent() {
        return buttons;
    }

    private void updateButtons() {
        List<Button> buildOptions = new ArrayList<>();
        activeMapObject.getActionNames().stream().
        forEach(mapObject -> {
            if(((String)mapObject).startsWith("build")) {
                buildOptions.add(makeButton(mapObject));
            }
        });
        baseButtons = buildOptions;
        buttons.setItems(FXCollections.observableList(baseButtons));
    }
    private Button makeButton(String name) {
        Button button = new Button();
        button.setText(name);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<TargetCoordinateSingle> targets = getMyController().setActionInProgress(name, activeMapObject);
                getMyController().getMap().highlightCells(targets);
            }
        });
        return button;
    }

    @Override
    public void update (Observable o, Object arg) {
        if (arg!=null) {
            activeMapObject = (PlayerMapObjectInterface)arg;
        }
        updateButtons();
    }
}
