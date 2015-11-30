package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;

public interface DisplayComponent {
    public Node getNodeToDraw();
    public boolean listensToMap();
    public ListChangeListener getListener();
    public void update();
}
