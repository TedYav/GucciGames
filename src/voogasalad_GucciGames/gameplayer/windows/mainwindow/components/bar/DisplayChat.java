package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class DisplayChat  implements DisplayComponent{
    private VBox chat;
    private TextArea inputArea;
    private ListView<String> chatHistory;
    private ResourceBundle myBundle=ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.LeftBar");
    private ResourceBundle myCssBundle = ResourceBundle.getBundle(myBundle.getString("cssclass"));

    public DisplayChat() {
        chat = new VBox();
        inputArea=new TextArea();
        inputArea.setPromptText(myBundle.getString("chatprompt"));
        //inputArea.setMaxWidth(200);
        inputArea.prefHeightProperty().set(Integer.parseInt(myBundle.getString("chatprefheight")));
        chatHistory=new ListView<String>();
        chat.getChildren().add(chatHistory);
        chat.getChildren().add(inputArea);
        chat.getStyleClass().add(myCssBundle.getString("left-chat-vbox"));
        inputArea.getStyleClass().add(myCssBundle.getString("chatinput"));
        chatHistory.getStyleClass().add(myCssBundle.getString("chathistory"));
    }

    @Override
    public Node getNodeToDraw() {
        return chat;
    }

}