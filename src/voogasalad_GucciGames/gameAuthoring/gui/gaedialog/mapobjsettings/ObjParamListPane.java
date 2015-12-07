package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ObjParamListPane extends GridPane {
	
	private List<ObjParam> param;
	private Map<Label, TextField> contents = new HashMap<Label, TextField>();
	private List<String> objName;
	private List<ObjType> type;
	
	private MapObjectType mapObjectType;
	
	public ObjParamListPane(List<ObjParam> param){
		for (ObjParam eachObjParam : param){
			String paramName = eachObjParam.getName();
			objName.add(paramName);
			
			ObjType objType = eachObjParam.getObjType();
			type.add(objType);
		}
		
		this.param = param;	
		
		setContent();
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));
		
		init();
		
		
	}
	
//	public ObjParamListPane(ObjParam param, MapObjectType mapObjectType){
//		this.mapObjectType = mapObjectType;
//		this.param.add(param);
//		this.type.add(param.getObjType());
//		init();
//	}
//	
//	public ObjParamListPane(List<ObjParam> param, MapObjectType mapObjectType){
//		for (ObjParam eachObjParam : param){
//			String paramName = eachObjParam.getName();
//			objName.add(paramName);
//			
//			ObjType objType = eachObjParam.getObjType();
//			type.add(objType);
//		}
//		
//		this.param = param;
//		init();
//		
//	}
	
	private void init(){
		setContent();
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));	
	}
	
	private void setContent(){
		int i  = 1;
		
		for (ObjParam eachObjParam : param){
			for (Map.Entry<String, String> entry : eachObjParam.getAllParams().entrySet()) {
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
	}
	
	public List<ObjParamValue> getAllInputsList(){
		List<ObjParamValue> allObjParamValues = new ArrayList<ObjParamValue>();
		for(int i=0; i<objName.size(); i++){
			String currObjName = objName.get(i);
			ObjType currObjType = type.get(i);
			ObjParamValue currObjParamValue = new ObjParamValue(currObjName, currObjType, mapObjectType);
			
			Map<String, String> map = new HashMap<String, String>();
			contents.forEach((k,v) -> {
				map.put(k.getText(), v.getText());	
			});
			currObjParamValue.setParamValues(map);
			
			allObjParamValues.add(currObjParamValue);
		}
		
		return allObjParamValues;
	}
	
//	public ObjParamValue getAllInputs(){
//		ObjParamValue objParamValue = new ObjParamValue(objName, type, mapObjectType);
//		Map<String, String> map = new HashMap<String, String>();
//		contents.forEach((k,v) -> {
//			map.put(k.getText(), v.getText());	
//		});
//		objParamValue.setParamValues(map);
//		return objParamValue;
//	}

}