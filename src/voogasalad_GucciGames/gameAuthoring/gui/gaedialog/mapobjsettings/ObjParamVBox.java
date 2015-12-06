package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class ObjParamVBox extends VBox{
	
	private List<ObjParam> objParams = new ArrayList<ObjParam>();
	
	public ObjParamVBox(List<ObjParam> charParams){
		this.objParams = charParams;
		setLayout();
		setContents();
	}
	
	private void setLayout(){
		this.setSpacing(5);
		this.setPadding(new Insets(5,5,5,5));
	}
	
	private void setContents(){
		objParams.forEach(p -> {		
			ObjParamPane pane = new ObjParamPane(p);
			this.getChildren().add(pane);
		});
	}

}
