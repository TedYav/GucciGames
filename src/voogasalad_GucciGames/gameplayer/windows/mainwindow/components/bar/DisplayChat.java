package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class DisplayChat extends DisplayComponent implements Observer{
    private VBox chat;
    private TextArea inputArea;
    private ListView<String> chatHistory;
    private List<String> chatHistoryStrings;
    private ResourceBundle myBundle=PlayerConfig.load("components.Bar");
    private ResourceBundle myCssBundle = PlayerConfig.load(myBundle.getString("cssclass"));

    public DisplayChat (GameScene scene, GameControllerInterface controller) {
        super(scene,controller);
        getController().addChatObserver(this);
        chat = new VBox();
        inputArea=new TextArea();
        inputArea.setPromptText(myBundle.getString("chatprompt"));
        //inputArea.setMaxWidth(200);
        inputArea.prefHeightProperty().set(Integer.parseInt(myBundle.getString("chatprefheight")));
        inputArea.setOnKeyReleased(e->{
                if (e.getCode() == KeyCode.ENTER) {
                    updateArea();
                }});
        chatHistoryStrings = new ArrayList<String>();
        chatHistory=new ListView<String>(FXCollections.observableList(chatHistoryStrings));
        chat.getChildren().add(chatHistory);
        chat.getChildren().add(inputArea);
        chat.getStyleClass().add(myCssBundle.getString("left-chat-vbox"));
        inputArea.getStyleClass().add(myCssBundle.getString("chatinput"));
        chatHistory.getStyleClass().add(myCssBundle.getString("chathistory"));
    }
    private void updateArea() {
        getController().sendMessage(inputArea.getText());
        inputArea.clear();
    }

    @Override
    public Parent getParent() {
        return chat;
    }
    @Override
    public void update (Observable o, Object arg) {
        System.out.println("UPDATINGCHATTTTTTTTTTTT");
        chatHistoryStrings.add((String)arg);
        chatHistory.setItems(FXCollections.observableList(chatHistoryStrings));
        chatHistory.scrollTo(chatHistory.getItems().size()-1);
    }

}
