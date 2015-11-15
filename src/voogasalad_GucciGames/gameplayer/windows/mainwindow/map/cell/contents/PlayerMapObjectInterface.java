package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents;

import java.util.List;

public interface PlayerMapObjectInterface {
    public List<String> getAttributes(); //i.e. HP=100, Owning Player=1, ...
    public String getImageUrl();
}
