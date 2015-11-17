package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class ActionDisplay implements DisplayComponent, Observer {
    private GameControllerInterface myController;
    private PlayerMapObjectInterface activeMapObject;
    private ListView<Button> buttons;
	private List<Button> temp;
    private String css = "voogasalad_GucciGames/gameplayer/config/components/actionDisplay.css";

    
    public ActionDisplay(GameControllerInterface controller) {
		myController = controller;
		myController.addMOObserver(this);
        temp = new ArrayList<Button>();       
        buttons = new ListView<Button>(FXCollections.observableList(temp));
        
    }
    
    private Button makeButton(String name) {
        Button button = new Button();
        button.setText(name);
        button.getStyleClass().add(css);
        button.setId("action-button");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myController.setActionInProgress(name, activeMapObject);
                myController.getMap().highlightCells(activeMapObject.getActionTargets(name));
            }
        });
        return button;
    	
    }
    
    private void updateButtons() {
    	List<Button> updatedActions = new ArrayList<>();
    	activeMapObject.getActionNames().stream().
    		forEach(action -> updatedActions.add(makeButton(action)));
    	temp = updatedActions;
        buttons.setItems(FXCollections.observableList(temp));
    }
    
    @Override
    public Node getNodeToDraw() {
        return buttons;
    }

    @Override
    public void update (Observable o, Object arg) {
        if (arg!=null) {
        	activeMapObject = (PlayerMapObjectInterface)arg;
        }
        updateButtons();

    }

}
