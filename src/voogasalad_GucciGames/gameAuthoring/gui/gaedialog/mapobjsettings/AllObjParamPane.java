package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class AllObjParamPane extends VBox {

	private Label titleLbl;
	private List<ObjParam> objParams = new ArrayList<ObjParam>();
	private List<ObjParamPane> objPane = new ArrayList<ObjParamPane>();
	private Button saveBtn = new Button("Save All");
	private ISwitchSettingsPane controller;
	private List<ObjParamValue> allCharParams;
	private boolean check = true;

	private MapObjectType mapObjectType;

	public AllObjParamPane(ISwitchSettingsPane controller, List<ObjParam> charParams, List<ObjParamValue> allCharParams,
			MapObjectType mapObjectType) {
		this.mapObjectType = mapObjectType;
		init(controller, charParams, allCharParams);
	}

	public AllObjParamPane(List<ObjParam> params, String titleName) {
		this.titleLbl = new Label(titleName);
		this.titleLbl.setFont(new Font("Arial", 20));
		this.getChildren().add(titleLbl);
		this.objParams = params;
		setLayout();
		setContents();

	}

	private void init(ISwitchSettingsPane controller, List<ObjParam> charParams, List<ObjParamValue> allCharParams) {
		this.objParams = charParams;
		this.controller = controller;
		this.allCharParams = allCharParams;
		this.saveBtn.setOnAction(e -> {
			this.allCharParams.addAll(getAllParam());
			allCharParams.forEach(element -> {
				System.out.println("saving: " + element.getName());
			});

		});
		setLayout();
		setContents();
		this.getChildren().add(saveBtn);
		// controller.addSaveButton(ButtonType.FINISH);
	}

	private void setLayout() {
		this.setSpacing(5);
		this.setPadding(new Insets(5, 5, 5, 5));
	}

	private void setContents() {
		objParams.forEach(p -> {
			ObjParamPane pane = new ObjParamPane(p, this.mapObjectType);
			objPane.add(pane);
			this.getChildren().add(pane);
		});

	}

	private void checkAllInputs() {

		objPane.forEach(pane -> {
			if (!pane.checkAllInputs()) {
				check = false;
			}
		});

	}

	public boolean getCheckAllInputs() {
		checkAllInputs();
		return check;
	}

	public List<ObjParamValue> getAllParam() {
		List<ObjParamValue> data = new ArrayList<ObjParamValue>();
		objPane.forEach(pane -> data.add(pane.getAllInputs()));
		return data;
	}

}
