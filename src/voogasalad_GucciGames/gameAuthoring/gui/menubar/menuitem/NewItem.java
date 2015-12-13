package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

class NewItem extends MenuItem {
	NewItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
		setOnAction(e -> {
			Dialog dialog = new Dialog();
			dialog.showAndWait().ifPresent(text -> {
				controller.initGame(text);
			});
		});
	}

	private class Dialog extends javafx.scene.control.Dialog<String> {
		private GridPane myGrid;

		Dialog() {
			setTitle("New Game");
			setHeaderText("Parameters for New Game:");
			ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
			getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

			myGrid = new GridPane();
			myGrid.setHgap(10);
			myGrid.setVgap(10);
			myGrid.setPadding(new Insets(20, 150, 10, 10));

			TextField tf = new TextField();
			tf.setTooltip(new Tooltip("Enter a non-empty string"));
			myGrid.add(new Label("Name: "), 0, 0);
			myGrid.add(tf, 1, 0);

			Node okButton = getDialogPane().lookupButton(ok);
			okButton.setDisable(tf.getText().trim().isEmpty());

			getDialogPane().setContent(myGrid);

			tf.textProperty().addListener((ob, oV, nV) -> {
				okButton.setDisable(nV.trim().isEmpty());
			});

			Platform.runLater(() -> tf.requestFocus());

			setResultConverter(dialogButton -> {
				if (dialogButton == ok) {
					return tf.getText();
				}
				return null;
			});
		}
	}
}
