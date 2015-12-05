package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GroovyDialog extends javafx.scene.control.Dialog implements ISwitchGroovyPane{
	
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	private MainPane groovyMainPane;
	private String title = "Custom Groovy";
	private StackPane stackPane = new StackPane();
	
	public GroovyDialog(){
		super();
		groovyMainPane = new MainPane(this, WIDTH, HEIGHT);
		stackPane.setPrefSize(WIDTH, HEIGHT);
		stackPane.setPadding(new Insets(15,15,15,15));
		
		stackPane.getChildren().add(groovyMainPane);		
		this.getDialogPane().setContent(stackPane);
		setHeaderText("Create Custom Game Components Using Groovy");
		
		this.addBtn(ButtonType.CLOSE);
	}
	

	@Override
	public void switchGroovyPane(Object p, String title) {
		stackPane.getChildren().removeAll(stackPane.getChildren());
		stackPane.getChildren().add((Node) p);
		
		setHeaderText(title);
		StackPane.setMargin((Node) p , new Insets(8,8,8,8));
		
	}


	@Override
	public void addBtn(ButtonType t) {
		this.getDialogPane().getButtonTypes().add(t);
		
	}


}
