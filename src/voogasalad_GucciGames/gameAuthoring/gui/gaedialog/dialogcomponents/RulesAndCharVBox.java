package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.listelements.MainListView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.CharacteristicsParam;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

public class RulesAndCharVBox {
	
	private Text title = new Text("Rules and Characteristics");
	private MainListView rulesListView = new MainListView();
	private ScrollPane rulesScrollPane = new ScrollPane();
	private MainListView characteristicsListView = new MainListView();
	private ScrollPane characteristicsScrollPane = new ScrollPane();
	private Button saveBtn = new Button("Save");
	private List<CharacteristicsParam> charParams = new ArrayList<CharacteristicsParam>();
	
	public RulesAndCharVBox(Set<String> rules, Set<String> Characteristics){
		
	}
	
	private void loadCharacteristics(){
		
	}

}
