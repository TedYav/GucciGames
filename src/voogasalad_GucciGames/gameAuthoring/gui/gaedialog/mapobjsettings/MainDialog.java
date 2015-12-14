package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.IListener;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.RadioBtnField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class MainDialog extends AGaeDialog<String> {
	
	private static final String settingsPackagePath = 
			"voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.";
	private GridPane gridPane = new GridPane();	
	private RadioBtnField radioBtnField;
	private List<String> itemsList = new ArrayList<String>();	
	private Label title = new Label("Add Properties to Map Object");
	private Node saveBtnNode;
	
	private MapObjectType mapObjType;
	private ActionParamsValue actionParamsValue;
	private List<ObjParamValue> charParamValues = new ArrayList<ObjParamValue>();	
	private IDialogGaeController controller;


	
	public MainDialog(IDialogGaeController controller, MapObjectType mapObjType){
		this.controller = controller;
		this.mapObjType = mapObjType;
		this.actionParamsValue = new ActionParamsValue(mapObjType);
		this.charParamValues = new ArrayList<ObjParamValue>();
		itemsList.add("Action");
		itemsList.add("Characteristic");
		radioBtnField = new RadioBtnField(itemsList);
		title.setFont(new Font("Arial" , 20));

		
		final ButtonType nextBtn = new ButtonType("Next", ButtonData.NEXT_FORWARD);
		this.getDialogPane().getButtonTypes().add(nextBtn);
		saveBtnNode = getDialogPane().lookupButton(nextBtn);
		saveBtnNode.setDisable(radioBtnField.getSelected() != null);
		
		this.setResultConverter(dialogButton -> {
			if(dialogButton == nextBtn){
				if(radioBtnField.getSelected().equals("Action")){
					// create action dialog
					ActionDialog dialog = new ActionDialog(controller, mapObjType, actionParamsValue);
					dialog.show();
					return "Action";
				} else {
					// create characteristic dialog
					AddCharacteristicDialog dialog = new AddCharacteristicDialog(controller, mapObjType,
							actionParamsValue, charParamValues);
					dialog.show();
					return "Characteristic";
				}
				
			} else {
				return "";
			}
			
		});

		createListener();
		init();
			
	}
	
	private void init(){
		gridPane.getChildren().add(title);
		gridPane.add(radioBtnField, 0, 1);
		this.getDialogPane().setContent(gridPane);
	}
	


	private void createListener(){
		IListener listener = g -> {
			g.selectedToggleProperty().addListener((observable, oV, nV) ->{
				if(g.getSelectedToggle() != null){
					saveBtnNode.setDisable(false);
				}
			}
			);};
		this.radioBtnField.addListener(listener);
	}

	


	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub
		
	}

}
