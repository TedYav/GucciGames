package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class ActionDisplay implements DisplayComponent {

    private ListView<Button> list;
    
    public ActionDisplay() {
        List<Button> temp = new ArrayList<Button>();
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        temp.add(btn);
        list=new ListView<Button>(FXCollections.observableList(temp));
        
        
    }
    
    @Override
    public Node getNodeToDraw() {
        return list;
    }
	

}
