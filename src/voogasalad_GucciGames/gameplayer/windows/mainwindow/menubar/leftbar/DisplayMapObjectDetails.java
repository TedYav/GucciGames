package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.leftbar;

import javafx.scene.Node;
import javafx.scene.control.ListView;

public class DisplayMapObjectDetails  implements MiniDisplayComponent{
    private ListView<String> list;
    public DisplayMapObjectDetails() {
        
    }
    public Node getNodeToDraw() {
        return list;
    }
}
