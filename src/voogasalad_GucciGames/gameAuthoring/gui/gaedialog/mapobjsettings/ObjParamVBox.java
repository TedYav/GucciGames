package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
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
	
	public ObjParamVBox(ISwitchSettingsPane controller, List<ObjParam> charParams){
		this.objParams = charParams;
		this.controller = controller;
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
			ObjParamPane pane = new ObjParamPane(p);
			objPane.add(pane);
			this.getChildren().add(pane);
		});
	}
	
	protected List<ObjParamValue> getAllParam(){
		List<ObjParamValue> data  = new ArrayList<ObjParamValue>();
		objPane.forEach(pane -> data.add(pane.getAllInputs()));
		return data;
	}

}
