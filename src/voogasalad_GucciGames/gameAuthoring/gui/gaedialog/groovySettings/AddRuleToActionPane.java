package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AddRuleToActionPane extends GridPane {
	private DialogTableView dialogTableView;
	
	public AddRuleToActionPane(){
		super();
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));	
		setTableView();
	}
	
	private void setTableView(){
		//TODO: Get available actions from backend
		List<String> actions = new ArrayList<String>();
		this.dialogTableView = new DialogTableView(actions, "Actions");
		final Button addCustomBtn = new Button("Add Custom Rule");
		addCustomBtn.setOnAction(e -> {
			
		});
		dialogTableView.addControlButton(addCustomBtn);
		this.getChildren().add(dialogTableView);
	}

}
