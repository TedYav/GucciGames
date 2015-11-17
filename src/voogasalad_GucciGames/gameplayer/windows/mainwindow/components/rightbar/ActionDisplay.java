package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.PlayerMapObjectInterface;

public class ActionDisplay implements DisplayComponent, Observer {
    private GameControllerInterface myController;
	private List<String> currentActions;
    private ListView<Button> buttons;
	private List<Button> temp;
    
    public ActionDisplay(GameControllerInterface controller) {
		myController = controller;
		myController.addMOObserver(this);

    	
        temp = new ArrayList<Button>();
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        
        temp.add(btn);
        buttons = new ListView<Button>(FXCollections.observableList(temp));
        
    }
    
    private Button makeButton(String name) {
        Button button = new Button();
        button.setText(name);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myController.setActionInProgress(name);
                // TODO highlight places
            }
        });
        return button;
    	
    }
    
    private void updateButtons() {
    	List<Button> updatedActions = new ArrayList<>();
    	currentActions.stream().map(action -> updatedActions.add(makeButton(action)));
    	temp = updatedActions;
    }
    
    @Override
    public Node getNodeToDraw() {
        return buttons;
    }

    @Override
    public void update (Observable o, Object arg) {
        if (arg!=null && arg.getClass().equals(PlayerMapObjectInterface.class)) {
        	PlayerMapObjectInterface activeMapObject = (PlayerMapObjectInterface)arg;
        	currentActions = activeMapObject.getActionNames();
        	
        }
    }
	

}
