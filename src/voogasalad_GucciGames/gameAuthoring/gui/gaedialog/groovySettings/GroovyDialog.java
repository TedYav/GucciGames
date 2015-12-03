package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GroovyDialog extends javafx.scene.control.Dialog implements ISwitchGroovyPane{
	
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	
	private Stage groovyStage = new Stage();
	private Scene groovyScene;
	private MainPane groovyMainPane;
	private String title = "Custom Groovy";
	private StackPane stackPane = new StackPane();
	
	public GroovyDialog(){
		groovyMainPane = new MainPane(this, WIDTH, HEIGHT);
		stackPane.getChildren().add(groovyMainPane);
		groovyScene = new Scene(stackPane, WIDTH, HEIGHT);
		groovyStage.setScene(groovyScene);
		//StackPane.setAlignment(groovyMainPane, Pos.CENTER);
		StackPane.setMargin(groovyMainPane, new Insets(8,8,8,8));
		groovyStage.setTitle(title);
	}
	
	public void showGroovyDialog(){
		groovyStage.show();
	}

	@Override
	public void switchGroovyPane(Object p, String title) {
		stackPane.getChildren().removeAll(stackPane.getChildren());
		stackPane.getChildren().add((Node) p);
		StackPane.setMargin((Node) p , new Insets(8,8,8,8));
		groovyStage.setTitle(title);
		
	}


}
