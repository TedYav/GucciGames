package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.MainPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class SettingsDialog extends AGaeDialog implements ISwitchSettingsPane {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private IDialogGaeController controller;
	private Properties prop;
	private GaeDialogHelper helper = new GaeDialogHelper();
	private ScrollPane scrollPane = new ScrollPane();
	private MainPane mainPane;
	private MapObjectType mapObjType;
	private ActionParamsValue actionParamsValue;
	private List<ObjParamValue> charParamValues = new ArrayList<ObjParamValue>();

	@SuppressWarnings("unchecked")
	public SettingsDialog(IDialogGaeController controller, MapObjectType mapObjType) {
		super();
		this.actionParamsValue = new ActionParamsValue(mapObjType);
		this.mapObjType = mapObjType;
		prop = helper.loadProperties("dialogproperties/actionsettings.properties");
		this.controller = controller;

		this.setHeaderText("Map Object Settings");
		this.mainPane = new MainPane(this, prop, controller, mapObjType, this.actionParamsValue, this.charParamValues);
		scrollPane.setContent(mainPane);
		this.getDialogPane().setContent(scrollPane);
		this.getDialogPane().setPrefSize(WIDTH, HEIGHT);
		final ButtonType saveBtn = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().add(saveBtn);

		this.setResultConverter(dialogButton -> {
			if (dialogButton == saveBtn) {
				System.out.println("save");

				if (this.charParamValues.size() != 0) {
					// TODO: charParamValue
					charParamValues.forEach(e -> {
						System.out.println("char saving: " + e.getName());
						System.out.println("map obj type: " + e.getMapObjectType());
						e.getParamValues().forEach((k, v) -> {
							System.out.println("Saved k: " + k);
						});
						this.controller.getPropertiesInterface().addMapObjectCharacteristic(e);

					});
				}
				System.out.println("action name: ________" + actionParamsValue.getName());
				if (this.actionParamsValue.getName() != null) {
					System.out.println("----------------Saving Action --------------");
					actionParamsValue.print();
					this.controller.getPropertiesInterface().addActionParamValue(actionParamsValue);
				}

			}
			return null;
		});
	}

	@Override
	public void switchSettingsPane(Object n) {
		scrollPane.setContent((Pane) n);
		this.getDialogPane().setContent(scrollPane);

	}

	@Override
	public Optional<ButtonType> getDialogButtonResponse() {
		return this.showAndWait();
	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub...add some kind of listener
		ObservableList<MapObjectType> currTileList = controller.getImmutableTileTypes();
	}

	// @Override
	// public void addSaveButton(ButtonType save) {
	// if(!this.getDialogPane().getButtonTypes().contains(save)){
	// this.getDialogPane().getButtonTypes().add(save);
	// }
	//
	//
	// }

}
