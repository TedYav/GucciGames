package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.DependenciesPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CharacteristicPane extends GridPane {
	
	private Button saveBtn = new Button("Save Characteristics & Next");
	private DialogTableView dialogTableView;
	
	
	public CharacteristicPane(ISwitchSettingsPane switchPane, Properties prop){
		//TODO: get All available characteristics
		super();
		List<String> allChars = new ArrayList<String>();
		
		dialogTableView = new DialogTableView(allChars, "Characteristics");
		setLayout();
		addSaveAction();
	}
	
	private void setLayout(){
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));
		this.add(dialogTableView, 0, 0);
		this.add(saveBtn, 3, 3);
		
	}
	
	private void addSaveAction(){
		saveBtn.setOnAction(e -> {
			List<String> data = dialogTableView.getData();
			// TODO: save
		});
	}
	

}
