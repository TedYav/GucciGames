package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GroovyDialog extends javafx.scene.control.Dialog implements ISwitchGroovyPane{
	
	private static final int WIDTH = 550;
	private static final int HEIGHT = 300;
	
	private Stage groovyStage = new Stage();
	private Scene groovyScene;
	private MainPane groovyMainPane;
	private String title = "Custom Groovy";
	
	public GroovyDialog(){
		groovyMainPane = new MainPane(this, WIDTH, HEIGHT);
		groovyScene = new Scene(groovyMainPane, WIDTH, HEIGHT);
		groovyStage.setScene(groovyScene);
		groovyStage.setTitle(title);
	}
	
	public void showGroovyDialog(){
		groovyStage.show();
	}

	@Override
	public void switchGroovyPane(Object p, String title) {
		// TODO Auto-generated method stub
		groovyScene.setRoot((Parent) p);
		groovyStage.setTitle(title);
		
	}


}
