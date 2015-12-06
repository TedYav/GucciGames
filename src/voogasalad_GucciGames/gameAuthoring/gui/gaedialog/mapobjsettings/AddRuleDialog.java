package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddRuleDialog extends javafx.scene.control.Dialog {
	
	private GridPane gridPane = new GridPane();
	private ComboBox<String> dropDown;
	private TextField textField = new TextField();
	String selected = "";
	@SuppressWarnings("unchecked")
	public AddRuleDialog(List<String> availableRules, ActionParamsValue actionParamsValue){
		final ButtonType save = new ButtonType("Save", ButtonData.FINISH);
		this.getDialogPane().getButtonTypes().addAll(save, ButtonType.CANCEL);
		textField.setDisable(true);
		dropDown = new ComboBox<String>();
		dropDown.setItems(FXCollections.observableArrayList(availableRules));
		
		
		dropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
            	textField.setText(t1);           
            }    
        });
		this.setResultConverter(dialogButton -> {
			if (dialogButton == save) {
				actionParamsValue.addRule(selected);
			}
			return null;
		});
		
		this.gridPane.add(textField, 0,0);
		this.gridPane.add(dropDown, 0,1);
		this.getDialogPane().setContent(gridPane);
		setLayout();
		
	}
	
	private void setLayout(){
		this.gridPane.setHgap(5);
		this.gridPane.setVgap(5);
		this.gridPane.setPadding(new Insets(5,5,5,5));
		
		
	}

}
