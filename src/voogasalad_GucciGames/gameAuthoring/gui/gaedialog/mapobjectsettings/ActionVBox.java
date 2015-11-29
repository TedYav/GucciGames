package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ActionListView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ListItem;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ActionVBox extends VBox {	
	public static final int INDEX = 0;
	
	private Text title = new Text("Select Actions");	
	private DropDownMenuField availableActionsMenu;
	private ActionListView listView = new ActionListView();
	private ScrollPane scrollPane = new ScrollPane();
	private Button nextBtn;
	private ISwitchSettingsPane switchPaneInterface;
	
	private DialogElements dialogElements;
	
	
	
	
	public ActionVBox(DialogElements dialogElements, 
			ISwitchSettingsPane switchPaneInterface){
		this.dialogElements = dialogElements;
		this.switchPaneInterface = switchPaneInterface;
		//TODO: add prop file
		availableActionsMenu = new DropDownMenuField(dialogElements, null, null, listView);
		scrollPane.setContent(listView);
		nextBtn = new Button("Save & Next");
		addActionToNexBtn();		
		this.getChildren().addAll(title, availableActionsMenu, scrollPane);
		
	}
	
	private void addActionToNexBtn(){
		nextBtn.setOnAction(e -> switchPaneInterface.switchSettingsPane(INDEX));
		for(ListItem item: listView.getAllListItems()){
			
		}
		
	}
	

}
