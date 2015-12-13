package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

/**
 * Create a pane for all objParam object
 * 
 * @author
 *
 */
public class ObjParamListPane extends GridPane {
	private boolean checkInput = true;

	private List<ObjParam> param = new ArrayList<ObjParam>();
	private Map<Label, TextField> contents = new HashMap<Label, TextField>();
	private List<String> objName = new ArrayList<String>();
	private List<ObjType> type = new ArrayList<ObjType>(); 

	private MapObjectType mapObjectType;
	private int playerId;
	
	public ObjParamListPane(List<ObjParam> param) {
		for (ObjParam eachObjParam : param) {
			String paramName = eachObjParam.getName();
			objName.add(paramName);

			ObjType objType = eachObjParam.getObjType();
			type.add(objType);
		}

		this.param = param;

		setContent();
		init();
	}
	
	public ObjParamListPane(List<ObjParam> param, MapObjectType mapObjectType) {
		this.mapObjectType = mapObjectType;
		for (ObjParam eachObjParam : param) {
			String paramName = eachObjParam.getName();
			objName.add(paramName);

			ObjType objType = eachObjParam.getObjType();
			type.add(objType);
		}

		this.param = param;

		setContent();
		init();
	}


	public ObjParamListPane(List<ObjParam> param, int playerId) {
		for (ObjParam eachObjParam : param) {
			String paramName = eachObjParam.getName();
			objName.add(paramName);

			ObjType objType = eachObjParam.getObjType();
			type.add(objType);
		}

		this.param = param;
		this.playerId = playerId;

		setContent();
		init();
	}

	private void init() {
		setContent();
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5, 5, 5, 5));
	}

	private void setContent() {
		int i = 2;

		Label playerLabel = new Label("Player " + playerId);
		this.add(playerLabel, 0, 1);

		for (ObjParam eachObjParam : param) {
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
		this.listenForInputs();
	}
	
	private void listenForInputs() {
		for (Entry<Label, TextField> entry : contents.entrySet()) {
			entry.getValue().textProperty().addListener((ob, oV, nV) -> {
				if (nV.trim().isEmpty()) {
					this.checkInput = false;
				}
			});
		}
	}
	
	public boolean getInputCheck() {
		return checkInput;
	}

	public boolean checkAllInputs() {
		for (Entry<Label, TextField> entry : contents.entrySet()) {
			if (entry.getValue().getText().equals("")) {
				return false;
			}
		}
		return true;
	}

	public List<ObjParamValue> getAllInputsList() {
		List<ObjParamValue> allObjParamValues = new ArrayList<ObjParamValue>();
		for (int i = 0; i < objName.size(); i++) {
			String currObjName = objName.get(i);
			ObjType currObjType = type.get(i);
			ObjParamValue currObjParamValue = new ObjParamValue(currObjName, currObjType, mapObjectType);

			Map<String, String> map = new HashMap<String, String>();
			for (Label key : contents.keySet()) {
				if (!contents.get(key).getText().isEmpty())
					map.put(key.getText(), contents.get(key).getText());
			}
			currObjParamValue.setParamValues(map);
			allObjParamValues.add(currObjParamValue);

		}

		return allObjParamValues;
	}

}