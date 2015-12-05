package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad.util.reflection.Reflection;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class GeneralPane extends GridPane {
	
	private Map<String, String> data = new HashMap<String, String>();
	private Map<Label, TextArea> content = new HashMap<Label, TextArea>();
	private Label title;
	private ISwitchGroovyPane controller;
	private IDependencies addDepController;
	private static final String path = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.";
	
	List<String> attributes;
	
	public GeneralPane(List<String> attributes, ISwitchGroovyPane controller, IDependencies addDepController, String name){
		this.controller = controller;
		this.addDepController = addDepController;
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));
		this.attributes = attributes;
		title = new Label(name);
		title.setFont(new Font("Arial", 20));
		this.getChildren().add(title);
		addAttributes();
	}
	
	public Map<String, String> getUserData(){
		content.forEach((k, v) -> {
			data.put(k.getText(), v.getText());
		});
		return data;
	}
	
	private void addAttributes(){
		int i = 1;
		for(String s: attributes){
			Label prompt = new Label(s);
			TextArea textArea = new TextArea();
			content.put(prompt, textArea);
			this.add(prompt, 0, i);
			this.add(textArea, 1, i);
			i++;
		}
	}
	
	protected void init(List<String> items, String name, String title){
		final Button nextBtn = new Button("Next");
		nextBtn.setOnAction(e -> {
			Reflection reflection = new Reflection();	
			this.addDepController.setParams();		
			controller.switchGroovyPane(reflection.createInstance(path + "DependenciesPane",items, addDepController, name ),
					title);
		});
		
		this.add(nextBtn, 3,3);
		
	}
	
	
	

}
