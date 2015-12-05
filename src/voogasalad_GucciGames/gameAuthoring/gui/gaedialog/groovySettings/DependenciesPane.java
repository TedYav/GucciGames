package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;

public class DependenciesPane extends GridPane {
	private DialogTableView dialogTableView;
	private List<String> availableItems;
	private String title;
	private IDependencies controller;
	
	public DependenciesPane(List<String> availableItems, IDependencies controller, String title){
		super();
		this.controller = controller;
		this.availableItems = availableItems;
		this.title = title;
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));	
		setTableView();
		final Button saveBtn = new Button("Save");
		saveBtn.setOnAction(e -> {
			
			List<String> selected = this.dialogTableView.getData();
			controller.addDependencies(selected);
			//TODO: save to backend
		});
	
		this.add(saveBtn, 4, 4);
	}
	
	private void setTableView(){
		//TODO: Get available rules from backend
		this.dialogTableView = new DialogTableView(availableItems, title);
		this.getChildren().add(dialogTableView);
		
	}

}
