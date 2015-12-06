package voogasalad_GucciGames;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.main.GaeMain;
import voogasalad_GucciGames.gameplayer.PlayerMain;

public class Launcher {
    ResourceBundle myBundle = ResourceBundle.getBundle("resources.launcher");
    public Launcher(Stage stage) {
       GaeMain gae = new GaeMain();
       PlayerMain player = new PlayerMain();
       BorderPane pane = new BorderPane();
       stage.setScene(new Scene(pane));
       Button author = new Button(myBundle.getString("authoring"));
        author.setOnAction(e->{
            try{
              gae.start(new Stage());
            }
            catch (Exception ex){};
        });
        author.setPrefHeight(Integer.parseInt(myBundle.getString("height")));
        author.setGraphic(new ImageView("images/structures/city-4.png"));
       Button play = new Button(myBundle.getString("player"));
       play.setOnAction(e->{
           try{
             player.start(stage);
           }
           catch (Exception ex){};
       });
       play.setGraphic(new ImageView("images/units/pit_droid-0.png"));
       play.setPrefHeight(Integer.parseInt(myBundle.getString("height")));
       pane.setLeft(author);
       pane.setRight(play);
       Label name = new Label(myBundle.getString("company"));
       pane.setCenter(name);
       stage.setWidth(Integer.parseInt(myBundle.getString("width")));
       stage.setHeight(Integer.parseInt(myBundle.getString("height")));
       stage.show();
    }
}
