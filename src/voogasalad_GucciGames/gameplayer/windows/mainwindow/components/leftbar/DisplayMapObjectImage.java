package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DisplayMapObjectImage implements DisplayComponent{
    private List<String> myImageUrls;
    private HBox display;
    private GameControllerInterface myController;
    public DisplayMapObjectImage (List<String> imageUrls, GameControllerInterface controller) {
        display=new HBox();
        myImageUrls=imageUrls;
        myController=controller;
        updateImages();
    }
        public void updateImages() {
            Image buffer;
            display.getChildren().clear();
            for (String s: myImageUrls) {
                ImageView imgView;
                buffer = myController.requestImage(s);
                if (buffer==null) {
                    buffer=new Image(s);
                    imgView=new ImageView(s);
                    myController.addNewImage(s,buffer);
                }
                else {
                    imgView=new ImageView(buffer);
                }
                display.getChildren().add(imgView);
            }
        }

	@Override
	public Node getNodeToDraw() {
		return display;
	}

}
