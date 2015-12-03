package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class BuildUnitsDisplay extends DisplayComponent{
    private ListView<Button> buttons;
    private List<Button> baseButtons;
    public BuildUnitsDisplay (GameControllerInterface controller) {
        super(controller);
        baseButtons = new ArrayList<Button>();
        buttons = new ListView<Button>(FXCollections.observableList(baseButtons));
    }

    @Override
    public Node getNodeToDraw () {
        return buttons;
    }

    private void updateButtons() {
        List<Button> buildOptions = new ArrayList<>();
        getMyController().getUnitsToBuild().stream().
        forEach(mapObject -> buildOptions.add(makeButton(mapObject)));
        baseButtons = buildOptions;
        buttons.setItems(FXCollections.observableList(baseButtons));
    }
    private Button makeButton(String name) {
        Button button = new Button();
        button.setText(name);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getMyController().getEngine().performAction(action, mapObject, target)
                List<TargetCoordinateSingle> targets = getMyController().setActionInProgress(name, activeMapObject);
                getMyController().getMap().highlightCells(targets);
            }
        });
        return button;
    }
}
