package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.Properties;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TextInputField;

public class NewObjMakerPane extends GridPane {
	protected final Properties prop;
	protected final StringProperty myImgPath = new SimpleStringProperty();
	protected TextInputField name;
	protected TextInputField layer;
	protected final String type;
	protected final AGuiGaeController myController;
	protected GridPane myPane;

	public NewObjMakerPane(AGuiGaeController controller, Properties prop, String type) {
		this.prop = prop;
		this.type = type;
		myController = controller;
		initContent(type);
	}

	private void initContent(String type) {
		Text title = new Text(prop.getProperty("title"));
		title.setFont(new Font("Arial", 20));
		name = new TextInputField(prop, "name");
		add(title, 0, 0);
		add(name, 0, 1);
		myPane = getImgBrowser();
		add(myPane, 0, 2);
		if (!type.equals("tile")) {
			layer = new TextInputField(prop, "layer");
			add(layer, 0, 3);
		}

	}

	protected GridPane getImgBrowser() {
		GridPane pane = new GridPane();
		pane.setHgap(20);
		ImageView imgBrowser = new ImageView();
		imgBrowser.setFitHeight(40);
		imgBrowser.setFitWidth(40);
		myImgPath.addListener((ch, oV, nV) -> {
			if (nV == null)
				imgBrowser.setImage(null);
			else
				imgBrowser.setImage(myController.getResourceManager().getImage(nV));
		});
		pane.add(imgBrowser, 0, 0);
		Button btn = new Button("Image");
		btn.setOnAction(e -> {
			myController.getImageBrowseDialog(type + "s").showAndWait().ifPresent(s -> myImgPath.set(s));
		});
		pane.add(btn, 1, 0);
		return pane;
	}

	public String[] getUserInputData() {
		String[] data = new String[3];
		data[0] = name.getTextInput();
		data[1] = myImgPath.get();
		data[2] = type.equals("tile") ? "0" : layer.getTextInput();
		return data;
	}

}
