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

public class DefaultMakerDialogContent extends DialogContent {
	private VBox myContent;
	private Properties prop;
	private Property objProp;
	
	private ISaveObjProperty saveObjProp;
	private ISaveGroovy saveGroovy;
	private ISaveCustomObj saveCustomObj;
	
	
	public DefaultMakerDialogContent(Properties prop, Property objProp, 
			ISaveObjProperty saveObjProp, ISaveGroovy saveGroovy, ISaveCustomObj saveCustomObj){
		myContent = initContent();
		this.prop = prop;
		this.objProp = objProp;
		this.saveObjProp = saveObjProp;			
	}
	
	private VBox initContent(){
		VBox vbox = new VBox();
		Text titleTextElement = new Text(prop.getProperty("title"));
		 titleTextElement.setId("title");
		 TextField nameTextField = new TextField();	
		 nameTextField.textProperty().addListener((observable, oldValue, newValue)->{
			 System.out.println("changed");
			 saveObjProp.saveObjProperty("name", newValue);
		 });

		 HBox nameElement = createElement(prop.getProperty("name"), 
				 nameTextField, "hbox-element");
		 HBox imageElement = createElement(prop.getProperty("image"),
				 makeBrowseElement(prop, "browse", "filechoosertitle", saveObjProp), "hbox-element");
		return vbox;
	}
	
	protected VBox getContent(){
		return myContent;
	}

}
