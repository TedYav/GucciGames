package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameplayer.controller.GameControllerEngineInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;

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
                    Button enter = new Button(myConfig.getString("Ok"));
                    enter.setOnAction(eh->{
                        hostIP=input.getText();
                        System.out.println("IP"+hostIP);
                        try {
                        	getManager().getController().getEngine().setController((GameControllerEngineInterface) getManager().getController());
                            getManager().getController().getEngine().beClient(hostIP);
                            getManager().getController().loadDefaultLevel();
                            getManager().sceneFinished();
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        dialog.close();
                    });
                    dialogVbox.getChildren().add(new Text(myConfig.getString("IPPrompt")));
                    dialogVbox.getChildren().add(input);
                    dialogVbox.getChildren().add(enter);
                    dialogVbox.setOnKeyPressed(key->{if (key.getCode().equals(KeyCode.ENTER)){
                        enter.fire();
                    }
                    });
                    Scene dialogScene = new Scene(dialogVbox, Integer.parseInt(myConfig.getString("DialogWidth")), Integer.parseInt(myConfig.getString("DialogHeight")));
                    dialog.setScene(dialogScene);
                    dialog.show();
		});
	}
	
	@Override
    protected Map<String, MenuAction> buildOptionMap() {
		Map<String, MenuAction> optionMap = new LinkedHashMap<>();
		optionMap.put("Host Game", () -> {getManager().getController().getEngine().setController((GameControllerEngineInterface) getManager().getController());
			getManager().getController().getEngine().beHost();
											getManager().getController().loadDefaultLevel();
										getManager().sceneFinished();});
                optionMap.put("Join Game", () -> ipDialog.fire());
		optionMap.put("Back", () -> myManager.loadScene(myConfig.getString("PrevScene")));
		return optionMap;
	}
}
