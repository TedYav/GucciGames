package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class AddCharacteristicDialog extends javafx.scene.control.Dialog<List<ObjParamValue>> implements ISwitchSettingsPane {
	
	private CharacteristicPane pane;
	
	private ActionParamsValue actionParamsValue;
	private List<ObjParamValue> charParamValues;
	
	public AddCharacteristicDialog(IDialogGaeController controller, MapObjectType type, 
			ActionParamsValue actionParamsValue, List<ObjParamValue> charParamValues){
		this.charParamValues = charParamValues;
		pane = new CharacteristicPane(this, controller, null, type, charParamValues);
		this.actionParamsValue = actionParamsValue;
		this.getDialogPane().setContent(pane);
		this.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == ButtonType.FINISH) {
		    	System.out.println("clicked");
		    	pane.getAllValue().forEach(charParamValue -> {
		    		actionParamsValue.addCharacteristics(charParamValue);
		    	});
		    	return pane.getAllValue();		    	
		    }
		    return null;
		});
	}


	@Override
	public void switchSettingsPane(Object p) {		
		this.getDialogPane().setContent((Pane)p);	
	}

	@Override
	public Optional<ButtonType> getDialogButtonResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
