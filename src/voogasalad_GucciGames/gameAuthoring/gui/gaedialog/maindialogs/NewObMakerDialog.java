package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import javafx.scene.layout.GridPane;

public class NewObMakerDialog extends AGaeDialog<MapObjectType> {
	private Properties prop;
	private IDialogGaeController controller;
	private GridPane gridPane = new GridPane();
	private String myType;

	public NewObMakerDialog(AGuiGaeController controller, String type) {
		super();
		myType = type;
		GaeDialogHelper helper = new GaeDialogHelper();
		prop = helper.loadProperties("dialogproperties/tiledialogproperties.properties");
		this.controller = controller;
		if(type.equals("unit"))
			gridPane = new NewUnitMakerPane(controller, prop, type);
		else
			gridPane = new NewObjMakerPane(controller, prop, type);
		this.getDialogPane().setContent(gridPane);
		this.getDialogPane().getButtonTypes().add(mySave);
		setSaveAction();
	}

	@Override
	protected void setSaveAction() {
		this.setResultConverter(dialogButton -> {
			if (dialogButton == mySave) {
				String[] data = ((NewObjMakerPane) gridPane).getUserInputData();
				MapObjectType mapObjType = new MapObjectType(data[0], data[1], Integer.parseInt(data[2]));
				controller.getResourceManager().copyImageToGame(data[1]);
				controller.createCustomType(mapObjType, myType);
			}
			return null;
		});
	}

}
