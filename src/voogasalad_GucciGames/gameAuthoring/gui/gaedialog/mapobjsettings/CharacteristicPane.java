package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.DependenciesPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CharacteristicPane extends GridPane {
	
	private Button saveBtn = new Button("Save Characteristics & Next");
	private DialogTableView dialogTableView;
	private IDialogGaeController controller;
	ISwitchSettingsPane switchPane;
	private List<String> allChars = new ArrayList<String>();
	private AllObjParamPane objParamVBox;
	private List<ObjParamValue> allCharParams;
	private MapObjectType mapObjectType;
	
	
	public CharacteristicPane(ISwitchSettingsPane switchPane, 
			IDialogGaeController controller, Properties prop, MapObjectType mapObjectType, List<ObjParamValue> charParamValues){
		
		super();
		this.mapObjectType = mapObjectType;
		controller.getPropertiesInterface().getAllMapObjCharParams().
		forEach(e -> allChars.add(e.getName()));
		this.allCharParams = charParamValues;
		
		this.controller = controller;
		this.switchPane = switchPane;
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
			List<ObjParam> charParams =
					controller.getPropertiesInterface().getSelectedMapObjCharParams(data);

			objParamVBox = new AllObjParamPane(switchPane, charParams, this.allCharParams, this.mapObjectType);
			
			this.switchPane.switchSettingsPane(objParamVBox);
			
		});
	}
	
	public List<ObjParamValue> getAllValue(){
		return objParamVBox.getAllParam();
	}
	

}
