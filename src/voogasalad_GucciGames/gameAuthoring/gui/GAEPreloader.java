package voogasalad_GucciGames.gameAuthoring.gui;

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
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class GAEPreloader {
	private static final String PATH = "voogasalad_GucciGames/gameAuthoring/gui/guccigames.png";
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
		loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
		progressText = new Label("Loading . . .");
		splashLayout = new VBox();
		splashLayout.getChildren().addAll(splash, loadProgress, progressText);
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

//	private void showMainStage(ReadOnlyObjectProperty<ObservableList<String>> friends) {
//		mainStage = new Stage(StageStyle.DECORATED);
//		mainStage.setTitle("My Friends");
//		mainStage.getIcons().add(new Image(APPLICATION_ICON));
//
//		final ListView<String> peopleView = new ListView<>();
//		peopleView.itemsProperty().bind(friends);
//
//		mainStage.setScene(new Scene(peopleView));
//		mainStage.show();
//	}

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
