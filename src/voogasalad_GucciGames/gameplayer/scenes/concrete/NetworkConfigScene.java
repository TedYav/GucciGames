package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class NetworkConfigScene extends GameMenuScene {
	String hostIP;
	Button ipDialog;
	public NetworkConfigScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
		hostIP="";
		ipDialog=new Button();
		ipDialog.setOnAction(e-> {
		    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(window.getStage());
                    VBox dialogVbox = new VBox(20);
                    TextField input = new TextField();
                    Button enter = new Button("OK");
                    enter.setOnAction(eh->{
                        hostIP=input.getText();
                        System.out.println("IP"+hostIP);
                        try {
                            getManager().getController().getEngine().beClient(hostIP);
                            getManager().getController().loadDefaultLevel();
                            getManager().sceneFinished();
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        dialog.close();
                    });
                    dialogVbox.getChildren().add(new Text("Enter IP Address of Host (\'x.x.x.x\'):"));
                    dialogVbox.getChildren().add(input);
                    dialogVbox.getChildren().add(enter);
                    Scene dialogScene = new Scene(dialogVbox, 300, 200);
                    dialog.setScene(dialogScene);
                    dialog.show();
		});
	}
	
	@Override
    protected Map<String, MenuAction> buildOptionMap() {
		Map<String, MenuAction> optionMap = new LinkedHashMap<>();
		optionMap.put("Host Game", () -> {getManager().getController().getEngine().beHost();
											getManager().getController().loadDefaultLevel();
										getManager().sceneFinished();});
                //optionMap.put("Join Game", () -> {getManager().getController().getEngine().beClient("10.190.209.220");
                optionMap.put("Join Game", () -> {ipDialog.fire();
                });
		optionMap.put("Back", () -> myManager.loadScene(myConfig.getString("PrevScene")));
		return optionMap;
	}
}
