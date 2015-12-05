package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.FileBrowserField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * Create VBox with:
 * 1. name text box
 * 2. image text box
 * @author yingqi
 *
 */

public class NewObjMakerPane extends GridPane{
	private DialogElements dialogElements;
	
	
	public NewObjMakerPane(DialogElements dialogElements ){
		this.dialogElements = dialogElements;	
		initContent();		
	}
	
	private void initContent(){
		Text title = new Text(dialogElements.getDialogProperties().getProperty("title"));
		title.setId("title");
		TextInputField name = new TextInputField(dialogElements, "name");
		FileBrowserField fileBrowser = new FileBrowserField(dialogElements,"image", "browse", "filechoosertitle");
		this.getChildren().addAll(title, name, fileBrowser);
		
	}
	

}
