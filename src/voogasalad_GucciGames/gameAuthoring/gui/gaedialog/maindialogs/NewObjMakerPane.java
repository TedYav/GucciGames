package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.FileBrowserField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NewObjMakerPane extends GridPane {
	private Properties prop;
	private FileBrowserField fileBrowser;
	private TextInputField name;
	private TextInputField layer;
	private String type;

	public NewObjMakerPane(Properties prop, String type) {
		this.prop = prop;
		initContent(type);
		this.type = type;
	}

	private void initContent(String type) {
		Text title = new Text(prop.getProperty("title"));
		title.setFont(new Font("Arial", 20));
		name = new TextInputField(prop, "name");
		fileBrowser = new FileBrowserField(prop, "image", "browse", "filechoosertitle");
		add(title, 0, 0);
		add(name, 0, 1);
		add(fileBrowser, 0, 2);
		if (!type.equals("tile")) {
			layer = new TextInputField(prop, "layer");
			add(layer, 0, 3);
		}

	}

	public String[] getUserInputData(){
		String[] data = new String[3];
		data[0] = name.getTextInput();
		data[1] = fileBrowser.getPath();
		data[3] = type.equals("tile")?"0":layer.getTextInput();
		return data;
	}

}
