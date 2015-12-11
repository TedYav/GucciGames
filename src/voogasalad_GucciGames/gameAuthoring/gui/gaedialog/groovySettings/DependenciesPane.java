package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.AGroovyParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;

public class DependenciesPane extends GridPane {
	private DialogTableView dialogTableView;
	private List<String> availableItems;
	private String title;
	private IDependencies addDepController;
	private Button saveBtn ;
	private IDialogGaeController gaeController;
	private GroovyType groovyType;
	
	public DependenciesPane(List<String> availableItems, IDialogGaeController gaeController, IDependencies controller,
			GroovyType groovyType, String title){
		super();
		this.availableItems = availableItems;
		this.groovyType = groovyType;
		this.title = title;
		this.addDepController = controller;
		this.gaeController = gaeController;
		init();
	}
	
	private void init(){
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
			AGroovyParams param = addDepController.getGroovyParamObject();
			addDepController.getGroovyParamObject().setDependencies(selected);
			switch(groovyType){
			case ACTION:
				this.gaeController.getPropertiesInterface().addGroovyAction((GActionParams)param);
				((GActionParams) param).getRules().forEach(r -> System.out.println("rule added: " + r));
				System.out.println("saved action from frontend");
			default:
				System.out.println("default");

			}
			
		});
	}
	
	
	private void setTableView(){
		//TODO: Get available rules from backend
		this.dialogTableView = new DialogTableView(availableItems, title);
		this.getChildren().add(dialogTableView);
		
	}

}
