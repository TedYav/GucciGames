package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;
import voogasalad_GucciGames.gameAuthoring.properties.Property;
import java.util.Properties;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * Create VBox with:
 * 1. name text box
 * 2. image text box
 * @author yingqi
 *
 */

public class MakerDialog {
	private VBox content = new VBox();
	private DialogElements dialogElements;
	private DialogContent customContent;
	private GroovyTabPane groovyTabPane;
	private SaveField save;
	
	
	public MakerDialog(DialogElements dialogElements, DialogContent customContent, 
			GroovyTabPane groovyTabPane, SaveField save ){
		this.dialogElements = dialogElements;	
		this.customContent = customContent;
		this.groovyTabPane = groovyTabPane;
		this.save = save;
		initContent();		
	}
	
	private void initContent(){
		Text title = new Text(dialogElements.getDialogProperties().getProperty("title"));
		title.setId("title");
		TextInputField name = new TextInputField(dialogElements, "name");
		FileBrowserField fileBrowser = new FileBrowserField(dialogElements,"image", "browse", "filechoosertitle");
		content.getChildren().addAll(title, name.getContent(), fileBrowser.getContent(), customContent.getContent(), 
				groovyTabPane.getContent(), save.getContent());
		
	}
	
	protected VBox getContent(){
		return content;
	}

}
