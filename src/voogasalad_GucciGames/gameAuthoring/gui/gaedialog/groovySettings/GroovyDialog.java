package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.AGroovyParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;

public class GroovyDialog extends AGaeDialog implements ISwitchGroovyPane {

	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	private MainPane groovyMainPane;
	private String title = "Custom Groovy";
	private StackPane stackPane = new StackPane();
	private GridPane nextPane;
	private AGroovyParams param;
	private String nextTitle;
	private IDialogGaeController dialogController;

	// @SuppressWarnings("unchecked")
	public GroovyDialog(IDialogGaeController dialogController) {
		super();
		this.dialogController = dialogController;
		groovyMainPane = new MainPane(this, dialogController);
		stackPane.setPrefSize(WIDTH, HEIGHT);
		stackPane.setPadding(new Insets(15, 15, 15, 15));

		stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		stackPane.getChildren().add(groovyMainPane);
		this.getDialogPane().setContent(stackPane);

		setHeaderText("Create Custom Game Components Using Groovy");

	}

	@Override
	public void switchGroovyPane(Object p, String title) {

		stackPane.getChildren().removeAll(stackPane.getChildren());
		stackPane.getChildren().add((Node) p);

		setHeaderText(title);

	}

	@Override
	public Optional<ButtonType> getRet() {
		// TODO Auto-generated method stub
		return this.showAndWait();
	}

	@Override
	public void setNextPane(GridPane p, String title) {
		// TODO Auto-generated method stub
		this.nextPane = p;

		this.nextTitle = title;
	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub

	}

}
