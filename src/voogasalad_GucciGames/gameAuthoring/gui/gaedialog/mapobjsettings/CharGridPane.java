package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.CharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CharGridPane extends GridPane {
	
	private ObjParam param;
	private Map<Label, TextField> contents = new HashMap<Label, TextField>();
	private Label name;
	
	public CharGridPane(ObjParam param){
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));
		this.param = param;	
		setContent();
		
		
	}
	
	private void setContent(){
		int i  = 1;
		for (Map.Entry<String, String> entry : param.getAllParams().entrySet()) {
			Label label = new Label(entry.getKey()); 
			label.setPrefWidth(100);
			TextField textField = new TextField();
			contents.put(label, textField);
			this.add(label, 0, i);
			this.add(textField, 2, i);
			this.setHalignment(textField, HPos.RIGHT);
			i++;
		}		
	}
	
	public Map<String, String> getAllInputs(){
		Map<String, String> map = new HashMap<String, String>();
		contents.forEach((k,v) -> {
			map.put(k.getText(), v.getText());	
		});
		return map;
	}

}
