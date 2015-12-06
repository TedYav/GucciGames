package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class AddCharacteristicDialog extends javafx.scene.control.Dialog<List<ObjParamValue>> implements ISwitchSettingsPane {
	
	private CharacteristicPane pane;
	
	public AddCharacteristicDialog(IDialogGaeController controller, MapObjectType type){
		pane = new CharacteristicPane(this, controller, null, type);
		this.getDialogPane().setContent(pane);
		this.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		this.setResultConverter(dialogButton -> {
		    if (dialogButton == ButtonType.FINISH) {
		    	System.out.println("clicked");
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

	@Override
	public void addSaveButton(ButtonType save) {
		this.getDialogPane().getButtonTypes().add(save);		
	}
	

}
