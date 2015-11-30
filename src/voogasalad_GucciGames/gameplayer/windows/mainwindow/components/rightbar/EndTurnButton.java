package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class EndTurnButton implements DisplayComponent{
    Button endTurn;
    public EndTurnButton(GameControllerInterface controller) {
        endTurn=new Button("End turnnn");
        endTurn.setOnMouseClicked(e->{
            controller.endTurn();
        });
    }

    @Override
    public Node getNodeToDraw () {
        return endTurn;
    }

    @Override
    public boolean listensToMap () {
        return false;
    }

    @Override
    public ListChangeListener getListener () {
        return null;
    }

    @Override
    public void update () {
        return;
    }

}
