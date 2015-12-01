package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings;
import java.io.IOException;
import java.util.List;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ActionListView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ListItem;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ISwitchSettingsPane;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.xml.SAXHandler;
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
		availableActionsMenu = new DropDownMenuField(dialogElements, "action", "action_items", listView);
		scrollPane.setContent(listView);
		nextBtn = new Button("Save & Next");
		addActionToNextBtn();		
		this.getChildren().addAll(title, availableActionsMenu, scrollPane, nextBtn);
		
	}
	
	private void addActionToNextBtn(){
		nextBtn.setOnAction(e -> {
			switchPaneInterface.switchSettingsPane(INDEX);
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		    try {
		        SAXParser saxParser = saxParserFactory.newSAXParser();
		        SAXHandler handler = new SAXHandler(listView.getAllListItemsName());
		        saxParser.parse(new 
		        		File("/Users/yingqi/Documents/308Fall15/voogasalad_GucciGames/src/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/mapobjectsettings/xml/actionDependencies.xml"), 
		        		handler);
		        List<ActionParams> actionParams = handler.getActionParams();
		        for(ActionParams actionParam : actionParams){
		        	actionParam.print();
		        }
		           
		    } catch (ParserConfigurationException | SAXException | IOException ex) {
		        ex.printStackTrace();
		    }

		});

		
	}
	

}
