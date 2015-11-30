package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class EndTurnButton implements DisplayComponent{
    private Button endTurn;
    private ResourceBundle myBundle=ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.EndTurnButton");
    private GameControllerInterface myController;
    public EndTurnButton(GameControllerInterface controller) {
        myController=controller;
        endTurn=new Button(myBundle.getString("player") + myController.getEngine().getGameParameters().whoseTurn()+myBundle.getString("endturn"));
        endTurn.setOnMouseClicked(e->{
            controller.endTurn();
        });
    }
    
    @Override
    public void updateDisplay() {
        endTurn.setText(myBundle.getString("player") + myController.getEngine().getGameParameters().whoseTurn()+myBundle.getString("endturn"));
    }

    @Override
    public Node getNodeToDraw () {
        return endTurn;
    }

}
