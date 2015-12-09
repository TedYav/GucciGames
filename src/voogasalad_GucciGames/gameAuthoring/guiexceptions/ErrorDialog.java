package voogasalad_GucciGames.gameAuthoring.guiexceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ErrorDialog extends Alert {
	public ErrorDialog(Exception e) {
		super(AlertType.ERROR);
		setTitle("Error");
		setHeaderText("Error 错误 エラー Erreur");
		setContentText(e.toString());

		// Set expandable Exception into the dialog pane.
		getDialogPane().setExpandableContent(getContent(e));
		showAndWait();
	}

	private GridPane getContent(Exception e) {
		// Create expandable Exception.
		Label label = new Label("The exception stacktrace was:");
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionText = sw.toString();

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);
		return expContent;
	}
}
