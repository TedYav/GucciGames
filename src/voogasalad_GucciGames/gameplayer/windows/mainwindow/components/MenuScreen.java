package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class MenuScreen extends WindowComponent {

	// TODO: factor parent into superclass
	private StackPane myPane;
	private Pane myBackground;
	private StackPane myMenuStack;
	private VBox myMenu;

	private Map<String, MenuAction> myOptions;
	private List<Text> myText;
	private String myTitle;
	private Text myTitleText;
	private Text mySelectedText;

	private Timeline myTimeline;
	private int flashIndex = 1;
	private int maxFlashIndex;

	private int minSelectedIndex = 0;
	private int mySelectedIndex = 0;

	// avoids having handlers interfering with each other
	private boolean done = false;

	private ResourceBundle myConfig = PlayerConfig.load("components.MenuScreen");

	public MenuScreen(GameScene scene, GameControllerInterface controller, Map<String, MenuAction> options) {
		this(scene, controller, options, "");
	}

	public MenuScreen(GameScene scene, GameControllerInterface controller, Map<String, MenuAction> options,
			String title) {
		super(scene, controller);
		myOptions = options;
		myText = new ArrayList<>();
		myTitle = title;
		maxFlashIndex = Integer.parseInt(myConfig.getString("FlashColors"));
		initializePanes();
		drawTitle();
		drawMenu();
		sizeMenu();
		highlightSelected();
		activateHandlers();
	}

	private void activateHandlers() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getGameScene().getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> handleKeys(e));
	}

	private void handleKeys(KeyEvent e) {
		if (!done) {
			switch (e.getCode()) {
			case UP:
				moveUp();
				break;
			case DOWN:
				moveDown();
				break;
			case ENTER:
				System.out.println("MENUSCREEN ENTERRRRRRRRRRRRRRRRR");
				done = true;
				getGameScene().getScene().removeEventFilter(KeyEvent.KEY_PRESSED, event -> handleKeys(event));
				myOptions.get(myText.get(mySelectedIndex).getText()).activate();
				break;
			}
		}
	}

	private void moveUp() {
		System.out.println("GOING UP");
		if (mySelectedIndex > minSelectedIndex) {
			mySelectedIndex--;
			highlightSelected();
		}
	}

	private void moveDown() {
		if (mySelectedIndex < myText.size() - 1) {
			mySelectedIndex++;
			highlightSelected();
		}
	}

	private void highlightSelected() {
		stopTimeline();
		mySelectedText = myText.get(mySelectedIndex);
		myTimeline = new Timeline(new KeyFrame(Duration.millis(Double.parseDouble(myConfig.getString("FlashRate"))),
				e -> flash(mySelectedText)));
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.play();
	}

	private void flash(Text text) {
		flashIndex++;
		text.setFill(Color.web(PlayerConfig.getResourceNumber(myConfig, "FlashColor", flashIndex % maxFlashIndex)));
	}

	private void stopTimeline() {
		if (myTimeline != null) {
			myTimeline.stop();
		}
		if (mySelectedText != null) {
			mySelectedText.setFill(Color.web(myConfig.getString("DefaultColor")));
		}
	}

	public void setTitle(String title) {
		myTitle = title;
		myTitleText.setText(title);
		mySelectedIndex = 1;
		minSelectedIndex = 1;
	}

	private void drawTitle() {
		if (!myTitle.isEmpty()) {
			Text t = new Text(myTitle);
			t.getStyleClass().addAll("menutitle");
			myMenu.getChildren().add(t);
			myText.add(t);
			myTitleText = t;
			mySelectedIndex = 1;
			minSelectedIndex = 1;
		} else {
			mySelectedIndex = 0;
			minSelectedIndex = 0;
		}
	}

	private void drawMenu() {
		myOptions.keySet().forEach(s -> addToMenu(s, myOptions.get(s)));

	}

	private void sizeMenu() {
		double maxWidth = 0.0;
		double maxHeight = 0.0;
		double minHeight = Double.parseDouble(myConfig.getString("MinHeight"));
		for (Text t : myText) {
			maxWidth = (t.getBoundsInLocal().getWidth() > maxWidth) ? t.getBoundsInLocal().getWidth() : maxWidth;
			maxHeight += t.getBoundsInLocal().getHeight();
		}
		myMenu.setMaxHeight(Double.max(maxHeight, minHeight));
		myMenu.setMaxWidth(maxWidth);
	}

	private void addToMenu(String s, MenuAction action) {
		Text t = new Text(s);
		if (action != null) {
			t.getStyleClass().addAll("menutext");
			t.setOnMouseClicked(e -> action.activate());
		} else {
			t.getStyleClass().add("menutext-nohover");
		}
		myMenu.getChildren().add(t);
		myText.add(t);
	}

	private void initializePanes() {
		myPane = new StackPane();
		myBackground = new Pane();
		// myMenuStack = new StackPane();
		myMenu = new VBox();
		myMenu.getStyleClass().add("gamemenu");
		// myMenuStack.getChildren().add(myMenu);
		myPane.getChildren().addAll(myBackground, myMenu);
		setParent(myPane);
	}

}
