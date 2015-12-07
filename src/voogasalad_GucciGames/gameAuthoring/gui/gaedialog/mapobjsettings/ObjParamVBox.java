package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

public class ObjParamVBox extends VBox{
	
	private List<ObjParam> objParams = new ArrayList<ObjParam>();
	private List<ObjParamPane> objPane = new ArrayList<ObjParamPane>();
	private Button saveBtn = new Button("Save All");
	private ISwitchSettingsPane controller;
	private List<ObjParamValue> allCharParams;
	
	private MapObjectType mapObjectType;
	

	public ObjParamVBox(ISwitchSettingsPane controller, List<ObjParam> charParams, List<ObjParamValue> allCharParams, MapObjectType mapObjectType){
		this.mapObjectType = mapObjectType;
		init(controller, charParams, allCharParams);
	}
	
	private void init(ISwitchSettingsPane controller, List<ObjParam> charParams, List<ObjParamValue> allCharParams){
		this.objParams = charParams;
		this.controller = controller;
		this.allCharParams = allCharParams;
		this.saveBtn.setOnAction(e -> {
			System.out.println("saving char params");
			getAllParam().forEach(element -> {System.out.println("Saving: " + element.getName());});
			this.allCharParams.addAll(getAllParam());
			allCharParams.forEach(element -> {
				System.out.println("saving: " + element.getName());
			});
			
		});
		setLayout();
		setContents();
		controller.addSaveButton(ButtonType.FINISH);
	}
	
	private void setLayout(){
		this.setSpacing(5);
		this.setPadding(new Insets(5,5,5,5));
	}
	
	
	private void setContents(){
		objParams.forEach(p -> {	
			ObjParamPane pane = new ObjParamPane(p, this.mapObjectType);
			objPane.add(pane);
			this.getChildren().add(pane);
		});
		this.getChildren().add(saveBtn);
	}
	
	protected List<ObjParamValue> getAllParam(){
		List<ObjParamValue> data  = new ArrayList<ObjParamValue>();
		objPane.forEach(pane -> data.add(pane.getAllInputs()));
		return data;
	}

}
