package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public abstract class AbstractTab extends Tab implements ITab{
	protected List<String> allImagePaths;
	protected List<ImageView> allImageViews;
	protected GridPane myGridPane;
	
	AbstractTab() {
		allImageViews = new ArrayList<ImageView>();
		myGridPane = new GridPane();
		setContent(myGridPane);
	}
	
	protected void addImages() {
		int i=0;
		int j=0;
		for(String path : allImagePaths){
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
			ImageView imageView = new ImageView(image);
			allImageViews.add(imageView);
			
			imageView.setFitHeight(30);
			imageView.setFitWidth(30);
			
			myGridPane.add(imageView, i, j);
			if(i>4){ i=0;j++;}
			else{
				i++;
			}
		}
	}
	
}
