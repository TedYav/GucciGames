package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.FileBrowserField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class NewObjMakerPane extends GridPane{
	private Properties prop;
	private FileBrowserField fileBrowser;
	private TextInputField name ;
	
	
	public NewObjMakerPane(Properties prop){
		this.prop = prop;
		initContent();		
	}
	
	private void initContent(){
		Text title = new Text(prop.getProperty("title"));
		title.setFont(new Font("Arial" , 20));
		name = new TextInputField(prop, "name");
		fileBrowser = new FileBrowserField(prop,"image", "browse", "filechoosertitle");
		this.add(title, 0, 0);
		this.add(name, 0, 1);
		this.add(fileBrowser, 0, 2);	
		
	}
	
	public String[] getUserInputData(){
		String[] data = new String[2];
		data[0] = name.getTextInput();
		data[1] = fileBrowser.getPath();
		return data;
	}
	

}
