package voogasalad_GucciGames.gameAuthoring.gui.util;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class GAEPreloader {
	private static final String PATH = "voogasalad_GucciGames/gameAuthoring/gui/util/guccigames.png";
	private Pane splashLayout;
	private ProgressBar loadProgress;
	private Label progressText;
	private static final int SPLASH_WIDTH = 1000;
	private static final int SPLASH_HEIGHT = 600;

	public GAEPreloader() {
		ImageView splash = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(PATH)));
		splash.setFitWidth(SPLASH_WIDTH);
		splash.setFitHeight(SPLASH_HEIGHT - 20);
		loadProgress = new ProgressBar();
		loadProgress.setPrefWidth(SPLASH_WIDTH);
		progressText = new Label("Loading . . .");
		splashLayout = new VBox();
		StackPane pane = new StackPane();
		Rectangle rect = new Rectangle(SPLASH_WIDTH, 40);
		rect.setFill(Color.BLACK);
		Text text = new Text(
				"Authors:\tEfe Aras John Dai Karen Li Tina Liang Mike Ma Daniel Mckee\n\t\tSally Merza Joy Patel Ying Qi Ted Yavuzkurt");
		text.setFill(Color.WHITE);
		pane.getChildren().addAll(rect, text);
		splashLayout.getChildren().addAll(splash, pane, loadProgress, progressText);
		progressText.setAlignment(Pos.CENTER);
		splashLayout.setEffect(new DropShadow());
	}

	public void start(final Stage initStage, InitCompletionHandler action) throws Exception {
		final Task<String> task = new Task<String>() {
			@Override
			protected String call() throws InterruptedException {

				updateMessage("Loading . . .");
				for (int i = 0; i < 100; i++) {
					Thread.sleep(20);
					updateProgress(i + 1, 100);
					updateMessage("Loading " + i + "%");
				}
				Thread.sleep(200);
				updateMessage("Load complete.");
				return null;
			}
		};

		showSplash(initStage, task, action);
		new Thread(task).start();
	}

	private void showSplash(final Stage initStage, Task<?> task, InitCompletionHandler initCompletionHandler) {
		progressText.textProperty().bind(task.messageProperty());
		loadProgress.progressProperty().bind(task.progressProperty());
		task.stateProperty().addListener((observableValue, oldState, newState) -> {
			if (newState == Worker.State.SUCCEEDED) {
				loadProgress.progressProperty().unbind();
				loadProgress.setProgress(1);
				initStage.toFront();
				FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
				fadeSplash.setFromValue(1.0);
				fadeSplash.setToValue(0.0);
				fadeSplash.setOnFinished(actionEvent -> initStage.hide());
				fadeSplash.play();
				initCompletionHandler.complete();
			}
		});
		Scene splashScene = new Scene(splashLayout);
		initStage.initStyle(StageStyle.UNDECORATED);
		final Rectangle2D bounds = Screen.getPrimary().getBounds();
		initStage.setScene(splashScene);
		initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
		initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
		initStage.show();
	}

	public interface InitCompletionHandler {
		public void complete();
	}
}
