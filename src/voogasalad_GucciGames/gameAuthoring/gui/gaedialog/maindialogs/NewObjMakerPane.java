package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.FileBrowserField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
	private Properties prop;
	
	
	public NewObjMakerPane(Properties prop){
		this.prop = prop;
		initContent();		
	}
	
	private void initContent(){
		Text title = new Text(prop.getProperty("title"));
		title.setFont(new Font("Arial" , 20));
		TextInputField name = new TextInputField(prop, "name");
		FileBrowserField fileBrowser = new FileBrowserField(dialogElements,"image", "browse", "filechoosertitle");
		this.getChildren().addAll(title, name, fileBrowser);
		this.add(name, 0, 1);
		this.add(fileBrowser, 0, 2);
		
		
	}
	

}
