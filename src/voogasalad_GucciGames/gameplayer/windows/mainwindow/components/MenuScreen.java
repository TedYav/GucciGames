package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.helpers.FontManager;

public class MenuScreen extends WindowComponent {

	// TODO: factor parent into superclass
	private StackPane myPane;
	private Pane myBackground;
	private StackPane myMenuStack;
	private VBox myMenu;
	
	private Map<String, MenuAction> myOptions;
	private List<Text> myText;
	private String myTitle;
	
	private ResourceBundle myConfig = PlayerConfig.load("components.MenuScreen");
	
	public MenuScreen(GameScene scene, GameControllerInterface controller, Map<String, MenuAction> options) {
		this(scene, controller, options, "");
	}
	
	public MenuScreen(GameScene scene, GameControllerInterface controller, Map<String, MenuAction> options, String title) {
		super(scene, controller);
		myOptions = options;
		myText = new ArrayList<>();
		myTitle = title;
		initializePanes();
		drawTitle();
		drawMenu();
		sizeMenu();
	}

	private void drawTitle() {
		if(!myTitle.isEmpty()){
			Text t = new Text(myTitle);
			t.getStyleClass().addAll("menutitle");
			myMenu.getChildren().add(t);
			myText.add(t);
		}
	}

	private void drawMenu() {
		myOptions.keySet().forEach(s->addToMenu(s, myOptions.get(s)));
	}
	
	private void sizeMenu() {
		double maxWidth = 0.0;
		double maxHeight = 0.0;
		double minHeight = Double.parseDouble(myConfig.getString("MinHeight"));
		for(Text t : myText){
			maxWidth = (t.getBoundsInLocal().getWidth()>maxWidth)?t.getBoundsInLocal().getWidth():maxWidth;
			maxHeight += t.getBoundsInLocal().getHeight();
		}
		myMenu.setMaxHeight(Double.max(maxHeight, minHeight));
		myMenu.setMaxWidth(maxWidth);
	}

	private void addToMenu(String s, MenuAction action) {
		Text t = new Text(s);
		if(action != null){
			t.getStyleClass().addAll("menutext");
			t.setOnMouseClicked(e -> action.activate());
		}
		else{
			t.getStyleClass().add("menutext-nohover");
		}
		myMenu.getChildren().add(t);
		myText.add(t);
	}

	private void initializePanes() {
		myPane = new StackPane();
		myBackground = new Pane();
		//myMenuStack = new StackPane();
		myMenu = new VBox();
		myMenu.getStyleClass().add("gamemenu");
		//myMenuStack.getChildren().add(myMenu);
		myPane.getChildren().addAll(myBackground, myMenu);
		setParent(myPane);
	}


}
