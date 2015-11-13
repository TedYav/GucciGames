package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

/** Constructs a scene with a pannable Map background. */
public class GUIMap extends ScrollPane implements IGUIMap {
	private IGuiGaeController myController;
	private StackPane myLayout;

	public GUIMap(IGuiGaeController controller) {
		setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		setPannable(true);
		// center the scroll contents.
		setHvalue(getHmin() + (getHmax() - getHmin()) / 2);
		setVvalue(getVmin() + (getVmax() - getVmin()) / 2);
		// construct the scene contents over a stacked background.
		Image backgroundImage = new Image("http://www.narniaweb.com/wp-content/uploads/2009/08/NarniaMap.jpg");
		
		StackPane layout = new StackPane();
		layout.getChildren().setAll(new ImageView(backgroundImage));
		setContent(layout);

		// wrap the scene contents in a pannable scroll pane.
		//ScrollPane scroll = createScrollPane(layout);

		// bind the preferred size of the scroll area to the size of the scene.
		//scroll.prefWidthProperty().bind(scene.widthProperty());
		//scroll.prefHeightProperty().bind(scene.widthProperty());

		
	}

	/**
	 * @return a control to place on the scene.
	 */
	private Button createKillButton() {
		final Button killButton = new Button("Kill the evil witch");
		killButton.setStyle("-fx-base: firebrick;");
		killButton.setTranslateX(65);
		killButton.setTranslateY(-130);
		killButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				killButton.setStyle("-fx-base: forestgreen;");
				killButton.setText("Ding-Dong! The Witch is Dead");
			}
		});
		return killButton;
	}

	/**
	 * @return a ScrollPane which scrolls the layout.
	 */
	private ScrollPane createScrollPane(Pane layout) {
		ScrollPane scroll = new ScrollPane();
		
		scroll.setPrefSize(800, 600);
		scroll.setContent(layout);
		return scroll;
	}

	@Override
	public void setMapObjectForPosition(GUIMapObject obj, Point2D pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMapObjectAtPosition(GUIMapObject obj, Point2D pos) {
		// TODO Auto-generated method stub
		
	}
}