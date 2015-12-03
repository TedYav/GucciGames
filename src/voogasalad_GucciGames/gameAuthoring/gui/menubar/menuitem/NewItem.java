package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

class NewItem extends MenuItem {
	NewItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
		setOnAction(e -> {
			Dialog dialog = new Dialog();
			dialog.showAndWait().ifPresent(map -> {
				controller.initGrid(map.get("width"), map.get("height"));
			});
		});
	}

	private class Dialog extends javafx.scene.control.Dialog<Map<String, Integer>> {
		private GridPane myGrid;
		protected Map<String, TextField> myTexts;
		protected int myNumEntries;

		Dialog() {
			setTitle("New Map");
			setHeaderText("Parameters for the map:");
			ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
			getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);
			myTexts = new HashMap<>();

			myGrid = new GridPane();
			myGrid.setHgap(10);
			myGrid.setVgap(10);
			myGrid.setPadding(new Insets(20, 150, 10, 10));

			addTexts();

			Node okButton = getDialogPane().lookupButton(ok);
			okButton.setDisable(!validate());

			getDialogPane().setContent(myGrid);

			myTexts.forEach((k, v) -> {
				v.textProperty().addListener((ob, oV, nV) -> {
					okButton.setDisable(nV.trim().isEmpty() || !validate());
				});
			});

			Platform.runLater(() -> myTexts.get("width").requestFocus());

			setResultConverter(dialogButton -> {
				if (dialogButton == ok) {
					Map<String, Integer> map = new HashMap<>();
					myTexts.forEach((k, v) -> {
						map.put(k, Integer.parseInt(v.getText()));
					});
					return map;
				}
				return null;
			});
		}

		private void addTextField(String name, String label, String defaultValue, String prompt, String range) {
			TextField tf = new TextField(defaultValue);
			tf.setPromptText(prompt);
			tf.setTooltip(new Tooltip(prompt + "\n" + range));
			myGrid.add(new Label(label), 0, myNumEntries);
			myGrid.add(tf, 1, myNumEntries);
			myTexts.put(name, tf);
			myNumEntries++;
		}

		private void addTexts() {
			addTextField("width", "Width:", "10", "Number of Columns", "Integer between 1 and 100");
			addTextField("height", "Height:", "10", "Number of Rows", "Integer between 1 and 100");
		}

		private boolean validate() {
			try {
				int w = Integer.parseInt(myTexts.get("width").getText());
				int h = Integer.parseInt(myTexts.get("height").getText());
				return w > 0 && w <= 100 && h > 0 && h <= 100;
			} catch (Exception e) {
				return false;
			}
		}
	}
}
