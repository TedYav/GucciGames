package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DisplayMapObjectImage implements DisplayComponent{
    private List<String> myImageUrls;
    private HBox display;
    private Map<String,ImageView> cachedImages;
    public DisplayMapObjectImage (List<String> imageUrls, Map<String,ImageView> images) {
        display=new HBox();
        cachedImages=images;
        myImageUrls=imageUrls;
        updateImages();
    }
        public void updateImages() {
            ImageView buffer;
            display.getChildren().clear();
            for (String s: myImageUrls) {
                buffer = cachedImages.get(s);
                if (buffer==null) {
                    buffer=new ImageView(s);
                    cachedImages.put(s, buffer);
                }
                display.getChildren().add(buffer);
            }
        }

	@Override
	public Node getNodeToDraw() {
		return display;
	}

}
