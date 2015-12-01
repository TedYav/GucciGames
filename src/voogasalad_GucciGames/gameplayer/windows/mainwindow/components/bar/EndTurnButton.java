package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import voogasalad_GucciGames.gameplayer.config.Config;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class EndTurnButton extends DisplayComponent{
    private Button endTurn;
    private ResourceBundle myBundle=Config.load("components.EndTurnButton");
    public EndTurnButton(GameControllerInterface controller) {
        super(controller);
        endTurn=new Button(myBundle.getString("player") + getMyController().getEngine().getGameParameters().whoseTurn()+myBundle.getString("endturn"));
        endTurn.setOnMouseClicked(e->{
            controller.endTurn();
        });
    }
    
    @Override
    public void updateDisplay() {
        endTurn.setText(myBundle.getString("player") + getMyController().getEngine().getGameParameters().whoseTurn()+myBundle.getString("endturn"));
    }

    @Override
    public Node getNodeToDraw () {
        return endTurn;
    }

}
