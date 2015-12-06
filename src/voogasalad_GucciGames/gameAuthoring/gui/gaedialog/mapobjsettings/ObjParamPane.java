package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
/**
 * Create a pane for one objParam object
 * @author yingqi
 *
 */
public class ObjParamPane extends GridPane {
	
	private ObjParam param;
	private Map<Label, TextField> contents = new HashMap<Label, TextField>();
	private Label name;
	private String objName;
	private ObjType type;
	private MapObjectType mapObjectType;
	
	public ObjParamPane(ObjParam param){
		this.objName = param.getName();
		this.type = param.getObjType();
		this.param = param;	
		
		setContent();
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));
		
		init();
		
		
	}
	
	public ObjParamPane(ObjParam param, MapObjectType mapObjectType){
		this.mapObjectType = mapObjectType;
		this.param = param;
		this.type = param.getObjType();
		init();
		
	}
	
	private void init(){
		setContent();
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));
		
	}
	
	private void setContent(){
		int i  = 1;
		for (Map.Entry<String, String> entry : param.getAllParams().entrySet()) {
			Label label = new Label(entry.getKey()); 
			
			label.setPrefWidth(200);
			TextField textField = new TextField();
			contents.put(label, textField);
			this.add(label, 0, i);
			this.add(textField, 2, i);
			this.setHalignment(textField, HPos.RIGHT);
			i++;
		}		
	}
	
	public ObjParamValue getAllInputs(){
		ObjParamValue objParamValue = new ObjParamValue(objName, type, mapObjectType);
		Map<String, String> map = new HashMap<String, String>();
		contents.forEach((k,v) -> {
			map.put(k.getText(), v.getText());	
		});
		objParamValue.setParamValues(map);
		return objParamValue;
	}

}
