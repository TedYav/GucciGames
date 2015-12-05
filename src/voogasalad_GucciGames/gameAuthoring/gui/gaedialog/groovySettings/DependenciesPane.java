package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.IAddMapObjParam;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;

public class DependenciesPane extends GridPane {
	private DialogTableView dialogTableView;
	private List<String> availableItems;
	private String title;
	private IDependencies addDepController;
	private IAddMapObjParam addParamController;
	private Button saveBtn ;
	
	public DependenciesPane(List<String> availableItems, IDependencies controller, String title){
		super();
		this.addDepController = controller;
		init();
	}
	
	private void init(){
		this.availableItems = availableItems;
		this.title = title;
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));	
		setTableView();
		saveBtn = new Button("Save");
		this.addSaveDependencies();
		this.add(saveBtn, 4, 4);
		
	}
	
	private void addSaveDependencies(){
		saveBtn.setOnAction(e -> {
			
			List<String> selected = this.dialogTableView.getData();
			addDepController.addDependencies(selected);
			//TODO: save to backend
		});
	}
	
	
	private void setTableView(){
		//TODO: Get available rules from backend
		this.dialogTableView = new DialogTableView(availableItems, title);
		this.getChildren().add(dialogTableView);
		
	}

}
