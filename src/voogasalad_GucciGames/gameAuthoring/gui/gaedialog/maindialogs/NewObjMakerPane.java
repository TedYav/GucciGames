package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NewObjMakerPane extends GridPane {
	private final Properties prop;
	private final StringProperty myImgPath = new SimpleStringProperty();
	private TextInputField name;
	private TextInputField layer;
	private final String type;
	private final AGuiGaeController myController;

	public NewObjMakerPane(AGuiGaeController controller, Properties prop, String type) {
		this.prop = prop;
		initContent(type);
		this.type = type;
		myController = controller;
	}

	private void initContent(String type) {
		Text title = new Text(prop.getProperty("title"));
		title.setFont(new Font("Arial", 20));
		name = new TextInputField(prop, "name");
		add(title, 0, 0);
		add(name, 0, 1);
		add(getImgBrowser(),0,2);
		if (!type.equals("tile")) {
			layer = new TextInputField(prop, "layer");
			add(layer, 0, 3);
		}

	}
	
	private Pane getImgBrowser(){
		GridPane pane = new GridPane();
		pane.setHgap(20);
		ImageView imgBrowser = new ImageView();
		imgBrowser.setFitHeight(40);
		imgBrowser.setFitWidth(40);
		myImgPath.addListener((ch,oV,nV)->{
			if(nV==null)
				imgBrowser.setImage(null);
			else
				imgBrowser.setImage(myController.getResourceManager().getImage(nV));
		});
		pane.add(imgBrowser, 0, 0);
		Button btn = new Button("Select Image");
		btn.setOnAction(e -> {
			myController.getImageBrowseDialog(type+"s").showAndWait().ifPresent(s->myImgPath.set(s));
		});
		pane.add(btn, 1, 0);
		return pane;
	}

	public String[] getUserInputData(){
		String[] data = new String[3];
		data[0] = name.getTextInput();
		data[1] = myImgPath.get();
		data[3] = type.equals("tile")?"0":layer.getTextInput();
		return data;
	}

}
