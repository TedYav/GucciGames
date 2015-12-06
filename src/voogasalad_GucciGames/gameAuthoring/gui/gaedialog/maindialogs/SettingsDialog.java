package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.MainPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.ActionPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsDialog extends AGaeDialog implements ISwitchSettingsPane{

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
	public SettingsDialog(IDialogGaeController controller, MapObjectType mapObjType){
		super();
		this.actionParamsValue = new ActionParamsValue(mapObjType);
		this.mapObjType = mapObjType;
		prop = helper.loadProperties("dialogproperties/actionsettings.properties");			
		this.controller = controller;
		this.setHeaderText("Map Object Settings");
		this.mainPane = new MainPane(
				this, prop, controller, mapObjType, this.actionParamsValue, this.charParamValues);
		scrollPane.setContent(mainPane);
		this.getDialogPane().setContent(scrollPane);
		this.getDialogPane().setPrefSize(WIDTH, HEIGHT);
		
		
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == ButtonType.FINISH) {
		        System.out.println("save");
		        if(this.charParamValues.size() != 0){
		        	//TODO: charParamValue
		        	charParamValues.forEach(e -> {
		        		System.out.println("char saving: " + e.getName());
		        		e.getParamValues().forEach((k,v) -> {
		        			System.out.println("Saved k: " + k);
		        		});
		        	});
		        	
		        }
		        
		        if(this.actionParamsValue.getName() != null){
		        	//TODO: actionParamValue
		        	actionParamsValue.print();
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

	@Override
	public void addSaveButton(ButtonType save) {
		this.getDialogPane().getButtonTypes().add(save);
		
	}






}
