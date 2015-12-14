package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

/**
 * Create a pane for one objParam object
 * 
 * @author yingqi
 *
 */
public class ObjParamPane extends GridPane {
	private boolean checkInput = true;

	private ObjectParam param;
	private Map<Label, TextField> contents = new HashMap<Label, TextField>();
	private Label nameLbl;
	private String objName;
	private ObjType type;
	private MapObjectType mapObjectType;

	public ObjParamPane(ObjectParam param) {
		this.nameLbl = new Label(param.getName());
		nameLbl.setFont(new Font("Arial", 20));
		this.objName = param.getName();
		this.type = param.getObjType();
		this.param = param;

		init();

	}

	public ObjParamPane(ObjectParam param, MapObjectType mapObjectType) {
		this.nameLbl = new Label(param.getName());
		this.mapObjectType = mapObjectType;
		this.objName = param.getName();
		this.param = param;
		this.type = param.getObjType();
		init();

	}

	private void init() {
		setContent();
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5, 5, 5, 5));

	}

	private void setContent() {
		this.getChildren().add(nameLbl);
		int i = 1;
		for (Map.Entry<String, String> entry : param.getAllParams().entrySet()) {
			if (!entry.getKey().equals("")) {
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

	public ObjParamValue getAllInputs() {
		ObjParamValue objParamValue = new ObjParamValue(objName, type, mapObjectType);
		Map<String, String> map = new HashMap<String, String>();
		contents.forEach((k, v) -> {
			map.put(k.getText(), v.getText());
		});
		objParamValue.setParamValues(map);
		System.out.println("Param: " + objParamValue.getName());
		return objParamValue;
	}

}