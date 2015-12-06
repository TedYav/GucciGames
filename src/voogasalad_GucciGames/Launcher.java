package voogasalad_GucciGames;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.main.GaeMain;
import voogasalad_GucciGames.gameplayer.PlayerMain;

public class Launcher {

    public Launcher(Stage stage) {
       GaeMain gae = new GaeMain();
       PlayerMain player = new PlayerMain();
       BorderPane pane = new BorderPane();
       stage.setScene(new Scene(pane));
       Button author = new Button("Author");
        author.setOnAction(e->{
            try{
              gae.start(new Stage());
            }
            catch (Exception ex){};
        });
       Button play = new Button("Play");
       play.setOnAction(e->{
           try{
             player.start(stage);
           }
           catch (Exception ex){};
       });
       pane.setLeft(author);
       pane.setRight(play);
       Label name = new Label("GUCCI GAMES");
       pane.setCenter(name);
       stage.setWidth(800);
       stage.setHeight(500);
       stage.show();
    }
}
