package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.Properties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

public class NewUnitMakerPane extends NewObjMakerPane {

	private final IntegerProperty myRow = new SimpleIntegerProperty(-2);
	private ImageView myImgBrowser;
	private ContextMenu myMenu;

	public NewUnitMakerPane(AGuiGaeController controller, Properties prop, String type) {
		super(controller, prop, type);
		myRow.addListener((ch, oV, nV) -> {
			if (myImgPath.get()==null)
				removeImg();
			else{
				myImgPath.set(myImgPath.get().split(":")[0]+(nV.intValue()<0?"":":"+nV));
				addImg(myImgPath.get());
			}
		});
	}

	@Override
	protected GridPane getImgBrowser() {
		GridPane pane = new GridPane();
		pane.setHgap(20);
		Button btn = new Button("Image");
		btn.setOnAction(e -> {
			myController.getImageBrowseDialog(type + "s").showAndWait().ifPresent(s -> {
				myImgPath.set(s);
				myRow.set(-1);
			});
		});
		pane.add(btn, 1, 0);

		btn = new Button("Sprite");
		btn.setOnAction(e -> {
			myController.getImageBrowseDialog("sprites").showAndWait().ifPresent(s -> {
				myImgPath.set(s+":0");
				myRow.set(0);
				addImg(myImgPath.get());
			});
		});
		pane.add(btn, 2, 0);
		return pane;
	}

	private void removeImg() {
		myPane.getChildren().remove(myImgBrowser);
		myImgBrowser = null;
	}

	private void addImg(String URI) {
		if (myImgBrowser != null)
			removeImg();
		myImgBrowser = myController.requestImage(URI);
		if(URI.contains(":")){
			myMenu = new SelectMenu(4, myRow.get()); // I don't think s.getNumAnimation works. So just hard coded something
			myImgBrowser.setOnMousePressed(e->{
				if(e.isSecondaryButtonDown()){
					myMenu.show(myImgBrowser, e.getScreenX(),e.getScreenY());
				}	
			});
		}
		myImgBrowser.setFitHeight(40);
		myImgBrowser.setFitWidth(40);
		myPane.add(myImgBrowser, 0, 0);
	}
	
	private class SelectMenu extends ContextMenu{
		public SelectMenu(int n, int sel) {
			ToggleGroup group = new ToggleGroup();
			for(int i = 0;i<n;i++){
				RadioMenuItem item = new RadioMenuItem("Animation "+i);
				item.setUserData(i);
				item.setToggleGroup(group);
				if(i==sel)
					item.setSelected(true);
				getItems().add(item);
			}
			group.selectedToggleProperty().addListener((ob,oldV,newV)->{
				if (group.getSelectedToggle() != null) {
					myRow.set((int)newV.getUserData());
				}
			});
		}
	}

}
