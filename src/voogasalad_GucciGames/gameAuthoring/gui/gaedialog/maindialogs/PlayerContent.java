package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.FileBrowserField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ScrollBarField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerContent extends GridPane{
	private static final String gaeDialogPath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.";
	private static final String settingsPackagePath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.";

	TextInputField textInputField;
	FileBrowserField browserField;
	
	private DialogElements dialogElements;
	private Properties prop;
	
	private int playerNumber;
	
	private ScrollBarField scrollBarField;
	
	private IDialogGaeController controller;
	
	private List<ObjParam> myAllObjParams;
	private List<CheckBox> allCheckedBoxes = new ArrayList<CheckBox>();
	private List<String> allCheckedStrings = new ArrayList<String>();	
	
	public PlayerContent(int playerNumber, IDialogGaeController controller, Properties prop, List<ObjParam> objParamList){
		this.playerNumber = playerNumber;
		this.controller = controller;
		this.prop = prop;
		this.myAllObjParams = objParamList;
		init();
	}

	protected void init(){
		Text title = new Text ("Player " + playerNumber);
		title.setId("subtitle");
		textInputField = new TextInputField(prop, 
				"player"+playerNumber);
		
		browserField = new FileBrowserField(prop,"image", "browse", "filechoosertitle");
		scrollBarField = new ScrollBarField(prop, "nummoves", "nummoves_items");
		scrollBarField.addListenerForPlayer();
		
		this.getChildren().add(title);
		
		this.add(textInputField, 0, 1);
		this.add(browserField, 0, 2);
		this.add(scrollBarField, 0, 3);	
		
		int i = 4;
		for(ObjParam eachObjParam : myAllObjParams){
			for (Map.Entry<String, String> entry : eachObjParam.getAllParams().entrySet()) {
				CheckBox cb = new CheckBox(entry.getKey());
				allCheckedBoxes.add(cb);
				this.add(cb, 0, i);
				i++;
			}		
		}
		setCheckBoxListeners();
		setPaddings();
	}
	
	private void setPaddings(){
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));	
	}
	
	protected String getPlayerName(){
		return textInputField.getTextInput();
	}
	
	protected int getPlayerId(){
		return playerNumber;
	}
	
	protected String getPlayerImagePath(){
		return browserField.getPath();
	}
	
	protected int getNumMoves(){
		 return (int) scrollBarField.getSelectedDouble();
	}

	
	public void setDialogElements(DialogElements dialogElements) {
		this.dialogElements = dialogElements;
	}

	private void setCheckBoxListeners(){
		for(CheckBox cb : allCheckedBoxes){
			cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
				public void changed(ObservableValue<? extends Boolean> ov,
						Boolean old_val, Boolean new_val) {
					if(new_val){
						allCheckedStrings.add(cb.getText());
					}else{
						allCheckedStrings.remove(cb.getText());
					}
				}
			});
		}
	}
	
	public List<ObjParam> getAllCheckedPlayerChars(){
		List<ObjParam> checkedObjParam = new ArrayList<ObjParam>();
		for(ObjParam objParam : myAllObjParams){
			for (Map.Entry<String, String> entry : objParam.getAllParams().entrySet()) {
			if(allCheckedStrings.contains(entry.getKey())){
				checkedObjParam.add(objParam);
			}
			}
		}
		return checkedObjParam;
	}

}
